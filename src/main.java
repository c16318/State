

/**
 *
 * @author c16318
 */
public class main {
    public static void main(String[] args){
        SafeFrame frame = new SafeFrame("STate Sample");
        while(true){
            for(int hour = 0;hour < 24; hour++){
                frame.setClock(hour);  //時刻設定
                try{
                    Thread.sleep(1000);
                }catch(InterruptedException e){
                    
                }
            }
        }
    }
}
