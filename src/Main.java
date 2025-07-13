
import org.lwjgl.glfw.GLFWVidMode;

import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.opengl.GL;
import org.lwjgl.openvr.Texture;

import static org.lwjgl.opengl.GL11.*;





public class Main {
    public static void main(String[] args) {
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

        // passed the context from GL to window
        glfwMakeContextCurrent(window);
        GL.createCapabilities();
        texture textureId = new texture("assets/texture.png");

        glEnable(GL_TEXTURE_2D);
        glBindTexture(GL_TEXTURE_2D, textureId.getTextureIdId());

        long lastTime = System.nanoTime();

        // x and y axis variablesW

        float x = 0;
        float y = 0;

        float speed = 0.5f;

        while(!glfwWindowShouldClose(window)){
            long currentTime = System.nanoTime();
            float deltaTime = (currentTime - lastTime) / 1_000_000_000.0f;
            lastTime = currentTime;

            glfwPollEvents();
            // controls
            if(glfwGetKey(window, GLFW_KEY_W) == 1){
                y += (speed * deltaTime);
            }if(glfwGetKey(window, GLFW_KEY_S) == 1 ){
                y -= (speed * deltaTime);
            }if(glfwGetKey(window, GLFW_KEY_D) == 1){
                x += (speed * deltaTime);
            }if(glfwGetKey(window, GLFW_KEY_A) == 1){
                x -= (speed * deltaTime);
            }

            // clears background
            glClearColor(0f, 0f, 0f, 1f);
            glClear(GL_COLOR_BUFFER_BIT);

            // vertices // shapes

            glBegin(GL_QUADS);
            glTexCoord2f(0f, 0f);    glVertex2f(-0.3f + x, 0.6f + y);
            glTexCoord2f(0f, 1f);    glVertex2f(-0.3f + x, -0.6f + y);
            glTexCoord2f(1f, 1f);    glVertex2f(0.3f + x, -0.6f + y);
            glTexCoord2f(1f, 0f);    glVertex2f(0.3f + x, 0.6f + y);
            glEnd();

            // renders all gl above
            glfwSwapBuffers(window);
        }


    }
}
