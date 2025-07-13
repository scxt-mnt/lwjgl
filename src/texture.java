import java.io.IOException;
import java.nio.IntBuffer;

import org.lwjgl.opengl.EXTABGR;
import org.lwjgl.system.MemoryStack;
import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.*;

import static org.lwjgl.stb.STBImage.stbi_load;

public class texture {

    private int textureId = 0;

    public texture(String fileName){
        try(MemoryStack stacks = MemoryStack.stackPush()){
            IntBuffer width = stacks.mallocInt(1);
            IntBuffer height = stacks.mallocInt(1);
            IntBuffer channels = stacks.mallocInt(1);

            ByteBuffer image = stbi_load(fileName, width, height, channels, 4);

            textureId = glGenTextures();
            glBindTexture(GL_TEXTURE_2D, textureId);

            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP);

            glTexImage2D(
                    GL_TEXTURE_2D,
                    0,
                    GL_RGBA,
                    width.get(),
                    height.get(),
                    0,
                    GL_RGBA,
                    GL_UNSIGNED_BYTE,
                    image
            );
        }catch(Exception e){

        }


    }
    public int getTextureIdId(){
        return textureId;
    };



}