package main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class KeyHandler implements KeyListener{
    public boolean aupPressed = false, adownPressed = false, aleftPressed = false, arightPressed = false, bupPressed = false, bdownPressed = false, bleftPressed = false, brightPressed = false;
    public boolean aattackOnePressed = false, aattackTwoPressed = false, battackOnePressed = false, battackTwoPressed = false;
    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_Q){
            aattackOnePressed = true;
        }
        if(code == KeyEvent.VK_E){
            aattackTwoPressed = true;
        }
        if(code == KeyEvent.VK_U){
            battackOnePressed = true;
        }
        if(code == KeyEvent.VK_O){
            battackTwoPressed = true;
        }
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
        if(code == KeyEvent.VK_Q){
            aattackOnePressed = false;
        }
        if(code == KeyEvent.VK_E){
            aattackTwoPressed = false;
        }
        if(code == KeyEvent.VK_U){
            battackOnePressed = false;
        }
        if(code == KeyEvent.VK_O){
            battackTwoPressed = false;
        }
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
