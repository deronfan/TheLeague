import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class Controller implements KeyListener{
    boolean aupPressed = false, adownPressed = false, aleftPressed = false, arightPressed = false, bupPressed = false, bdownPressed = false, bleftPressed = false, brightPressed = false;
    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W){
            aupPressed = true;
        }
        if(code == KeyEvent.VK_A){
            aleftPressed = true;
        }
        if(code == KeyEvent.VK_S){
            adownPressed = true;
        }
        if(code == KeyEvent.VK_D){
            arightPressed = true;
        }
        if(code == KeyEvent.VK_I){
            bupPressed = true;
        }
        if(code == KeyEvent.VK_J){
            bleftPressed = true;
        }
        if(code == KeyEvent.VK_K){
            bdownPressed = true;
        }
        if(code == KeyEvent.VK_L){
            brightPressed = true;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W){
            aupPressed = false;
        }
        if(code == KeyEvent.VK_A){
            aleftPressed = false;
        }
        if(code == KeyEvent.VK_S){
            adownPressed = false;
        }
        if(code == KeyEvent.VK_D){
            arightPressed = false;
        }
        if(code == KeyEvent.VK_I){
            bupPressed = false;
        }
        if(code == KeyEvent.VK_J){
            bleftPressed = false;
        }
        if(code == KeyEvent.VK_K){
            bdownPressed = false;
        }
        if(code == KeyEvent.VK_L){
            brightPressed = false;
        }
    }    
}
