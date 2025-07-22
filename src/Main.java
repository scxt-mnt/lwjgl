
import org.lwjgl.glfw.GLFWVidMode;

import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.opengl.GL;
import org.lwjgl.openvr.Texture;

import static org.lwjgl.opengl.GL11.*;





public class Main {
    public static void main(String[] args) {
        float[] vertices = {
            0.5f, -0.5f, 1f, 0, 0, 1f, // RIGHT BOTTOM
           -0.5f, -0.5f, 1f, 0, 0, 1f, // BOTTOM LEFT
            0.0f,  0.5f, 1f, 0, 0, 1f  // MID TOP
        };




        if(!glfwInit()){
            throw new IllegalStateException("failed to execute glfw");
        }

        long window = glfwCreateWindow(640, 400, "glfw my Game", 0, 0);
        if(window == 0){
            System.out.println("failed to create window");
        }
        glfwWindowHint(GLFW_VISIBLE, 1);
        GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());

        glfwSetWindowPos(window, (vidMode.width() - 640) / 2, (vidMode.height() - 400) / 2 );


        glfwMakeContextCurrent(window);
        GL.createCapabilities();
        Shapes tria = new Shapes(vertices);



        while(!glfwWindowShouldClose(window)){
                glfwPollEvents();

                tria.render();


            // renders all gl above
            glfwSwapBuffers(window);
        }


    }
}
