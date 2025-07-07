import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import static org.lwjgl.opengl.GL11.*;

import static org.lwjgl.glfw.GLFW.*;

import static org.lwjgl.glfw.GLFW.*;
public class Main {
    public static void main(String[] args) {
        System.out.println("LWJGL Version: " + Version.getVersion());
            if(!glfwInit()){
                throw new IllegalStateException("failed to load glfw");
            }
            glfwWindowHint(GLFW_VISIBLE,GLFW_FALSE);
            long window = glfwCreateWindow(640, 400, "my glfw game", 0, 0);

            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            glfwSetWindowPos(window, (vidmode.width() - (640)) / 2, (vidmode.height() - (400)) / 2) ;
            glfwShowWindow(window);

            //PASS CONTEXT
            glfwMakeContextCurrent(window);
            GL.createCapabilities();


            //clear color to
            glClearColor(0f, 0f, 0f, 1f);

            float x = 0;
            float y = 0;

            while(!glfwWindowShouldClose(window)){
                glClear(GL_COLOR_BUFFER_BIT);
                glfwPollEvents();

                if(glfwGetKey(window, GLFW_KEY_RIGHT) == 1){
                    x += 0.001f;
                }
                if(glfwGetKey(window, GLFW_KEY_UP) == 1){
                    y += 0.001f;
                }
                if(glfwGetKey(window, GLFW_KEY_LEFT) == 1){
                    x -= 0.001f;
                }
                if(glfwGetKey(window, GLFW_KEY_DOWN) == 1){
                    y -= 0.001f;
                }
                glBegin(GL_QUADS);
                glColor4f(0f, 1f, 0f, 1f);
                glVertex2f(-0.5f + x, 0.5f + y);
                glColor4f(1f,0f,0f,1f);
                glVertex2f(-0.5f + x, -0.5f + y);
                glColor4f(0f,0f,1f,1f);
                glVertex2f(0.5f + x, -0.5f + y);
                glColor4f(0f, 0f, 0f, 1f);
                glVertex2f(0.5f + x, 0.5f + y);
                glEnd();

                glfwSwapBuffers(window);
            }
            glfwTerminate();


        }
}