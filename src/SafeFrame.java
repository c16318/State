import java.awt.Frame;
import java.awt.Label;
import java.awt.Color;
import java.awt.Button;
import java.awt.TextField;
import java.awt.TextArea;
import java.awt.Panel;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 *
 * @author c16318
 */
public class SafeFrame extends Frame implements ActionListener,Context{
    private TextField textClock = new TextField(60);
    private TextArea textScreen = new TextArea(10,60);
    private Button buttonUse = new Button("金庫使用");
    private Button buttonAlarm = new Button("非常ベル");
    private Button buttonPhone = new Button("通常通話");
    private Button buttonExit = new Button("終了");

    private State state = DayState.getInstance();
    
    public SafeFrame(String title){
        super(title);
        setBackground(Color.lightGray);
        setLayout(new BorderLayout());
        //textClock配置 
        add(textClock,BorderLayout.NORTH);
        textClock.setEditable(false);
        //textScreen
        add(textScreen,BorderLayout.CENTER);
        textScreen.setEditable(false);
        //パネルにボタンを格納
        Panel panel = new Panel();
        panel.add(buttonUse);
        panel.add(buttonAlarm);
        panel.add(buttonPhone);
        panel.add(buttonExit);
        //パネルを配置
        add(panel,BorderLayout.SOUTH);
        //表示
        pack();
        this.setVisible(true);
        //リスナーの設定
        buttonUse.addActionListener(this);
        buttonAlarm.addActionListener(this);
        buttonPhone.addActionListener(this);
        buttonExit.addActionListener(this);
    }
    
    //ボタンが押されたらここへくる
    public void actionPerformed(ActionEvent e){
        System.out.println(e.toString());
        if(e.getSource() == buttonUse){
            state.doUse(this);
        }
        else if(e.getSource() == buttonAlarm){
            state.doAlarm(this);
        }
        else if(e.getSource() == buttonPhone){
            state.doPhone(this);
        }
        else if(e.getSource() == buttonExit){
            System.exit(0);
        }
        else{
            System.out.println("?");
        }
    }
    
    //時刻の設定
    public void setClock(int hour){
        String clockstring = "現在の時刻は";
        if(hour < 10){
            clockstring += "0" + hour +":00";
        }
        else{
            clockstring += hour + ":00";
        }
        System.out.println(clockstring);
        textClock.setText(clockstring);
        state.doClock(this, hour);
    }
    
    //状態変化
    public void changeState(State state){
        System.out.println(this.state + "から" + state + "へ状態が変化しました。");
        this.state = state;
    }
    
    //センターの呼び出し
    public void callSecurityCenter(String msg){
        textScreen.append("call!" + msg + "\n");
    }
    
    //センター記録
    public void recordLog(String msg){
        textScreen.append("record...." + msg + "\n");
    }
}
