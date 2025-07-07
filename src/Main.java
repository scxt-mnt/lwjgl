import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
public class Main {
    public static void main(String[] args) {
        System.out.println("LWJGL Version: " + Version.getVersion());
        if(!glfwInit()){
            throw new IllegalStateException("failed to start glfw");
        }
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        long window = glfwCreateWindow(640, 400, "glfw game", 0, 0);
        if(window == 0){
            throw new IllegalStateException("failed to create window");
        }
        glfwMakeContextCurrent(window);

        GL.createCapabilities();

        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

        glfwSetWindowPos(window, (vidmode.width() - 640) / 2, ( vidmode.height() - 400) / 2);

        glfwShowWindow(window);

        while(!glfwWindowShouldClose(window)){
            glfwPollEvents();
            glfwSwapBuffers(window);
        }
            glfwTerminate();

        }
}