import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWVidMode;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.GL;


public class Main {
    public static void main(String[] args) {

        System.out.println("LWJGL Version: " + Version.getVersion());
        if(!glfwInit()){
            throw new IllegalStateException("failed to load glfw");
        }else{
            System.out.print("glfw is activated");
        };

        //screen settings
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        long window = glfwCreateWindow(620, 400, "my lwjgl game", 0, 0);
        GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(window, (vidMode.width() - 620) /2, (vidMode.height() - 400) / 2 );

        glfwShowWindow(window);


        glfwMakeContextCurrent(window);
        GL.createCapabilities();
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(-1, 1, -1, 1, -1, 1); // left, right, bottom, top, near, far
        glMatrixMode(GL_MODELVIEW);
        texture tex = new texture("assets/texture.png");


        //enable texture
        glEnable(GL_TEXTURE_2D);
        //bind texture
        glBindTexture(GL_TEXTURE_2D, tex.getId());



        float x = 0;
        float y = 0;
        float color_Blue = 0;

        while(!glfwWindowShouldClose(window)){
            glfwPollEvents();
            //controls
            if(glfwGetKey(window, GLFW_KEY_W) == 1){
               y += 0.001f;
            }
            if(glfwGetKey(window, GLFW_KEY_S) == 1){
               y -= 0.001f;
            }
            if(glfwGetKey(window,GLFW_KEY_D) == 1){
                x += 0.001f;
            }
            if(glfwGetKey(window,GLFW_KEY_A) == 1){
                x -= 0.001f;
            }
            if(glfwGetMouseButton(window,  0) == 1 ){
                color_Blue += 1f;
            }else{
                color_Blue = 0;
            }
            if(glfwGetKey(window, GLFW_KEY_ESCAPE) == GL_TRUE){
                glfwSetWindowShouldClose(window, true);
            }



            //clear color
            glClearColor(0, 0 ,color_Blue , 1f);
            glClear(GL_COLOR_BUFFER_BIT);
            //vertices
            glBegin(GL_QUADS);
            glTexCoord2f(0, 0); glVertex2f(-0.5f + x,  0.5f + y); // top-left
            glTexCoord2f(0, 1); glVertex2f(-0.5f + x, -0.5f + y); // bottom-left
            glTexCoord2f(1, 1); glVertex2f( 0.5f + x, -0.5f + y); // bottom-right
            glTexCoord2f(1, 0); glVertex2f( 0.5f + x,  0.5f + y); // top-right
            glEnd();
            // renders all above
            glfwSwapBuffers(window);
            }
        glfwTerminate();
      }
}
