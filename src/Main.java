
import org.lwjgl.glfw.GLFWVidMode;

import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.opengl.GL;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;


public class Main {
    private static int vertexShader;
    private static int fragShader;
    private static int shaderProgram;

    public static String loadFile(String filepath) throws IOException {
      return new String(Files.readAllBytes(Paths.get(filepath)));
    };

    public static void main(String[] args)  {
        float[] vertices = {
                0.5f, -0.5f, 1f, 0f, 0f,   // pos(x,y) + color(r,g,b)
                -0.5f, -0.5f, 0f, 1f, 0f,
                0.0f,  0.5f, 0f, 0f, 1f
        };






        if(!glfwInit()){
            throw new IllegalStateException("failed to execute glfw");
        }
        glfwWindowHint(GLFW_VISIBLE, 1);
        long window = glfwCreateWindow(640, 400, "glfw my Game", 0, 0);
        if(window == 0){
            System.out.println("failed to create window");
        }

        GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());

        glfwSetWindowPos(window, (vidMode.width() - 640) / 2, (vidMode.height() - 400) / 2 );

            glfwMakeContextCurrent(window);
            GL.createCapabilities();

            Shapes tria = new Shapes(vertices);

        try {
            vertexShader = glCreateShader(GL_VERTEX_SHADER);
            glShaderSource(vertexShader, loadFile("vertexShader.glsl"));
            glCompileShader(vertexShader);

            fragShader = glCreateShader(GL_FRAGMENT_SHADER);
            glShaderSource(fragShader, loadFile("fragShaders.glsl"));
            glCompileShader(fragShader);

                }catch(IOException e){
                e.printStackTrace();
        }


        shaderProgram = glCreateProgram();
        glAttachShader(shaderProgram, vertexShader);
        glAttachShader(shaderProgram, fragShader);
        glLinkProgram(shaderProgram);
        glUseProgram(shaderProgram);




        while(!glfwWindowShouldClose(window)){
                glfwPollEvents();

                tria.render();


            // renders all gl above
            glfwSwapBuffers(window);
        }


    }
}
