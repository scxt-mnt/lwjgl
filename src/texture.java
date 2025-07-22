import java.nio.IntBuffer;
import org.lwjgl.system.MemoryUtil;
import java.nio.ByteBuffer;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.stb.STBImage.stbi_load;
public class texture {

    private int textureId = 0;


    public texture(String fileName){

        IntBuffer  width = MemoryUtil.memAllocInt(1);
        IntBuffer  height = MemoryUtil.memAllocInt(1);
        IntBuffer  channels = MemoryUtil.memAllocInt(1);

        ByteBuffer image = stbi_load(fileName, width, height, channels, 4);

        if(image == null){
            throw new IllegalStateException("no image found");
        }


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



            if(image != null){
                MemoryUtil.memFree(image);
            }
            MemoryUtil.memFree(width);
            MemoryUtil.memFree(height);
            MemoryUtil.memFree(channels);


    }
    public int getTextureId(){
        return textureId;
    };



}