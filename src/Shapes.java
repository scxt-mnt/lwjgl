import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11C.GL_FLOAT;
import static org.lwjgl.opengl.GL15C.*;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;

public class Shapes {
    private int vboId;
    public Shapes(float[] shape){

        vboId = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboId);

        // make an memory java version
        FloatBuffer shapeBuffer = MemoryUtil.memAllocFloat(shape.length);

        // putting the coordinates to shapeBuffer
        shapeBuffer.put(shape).flip();

        // uploading vertices
        glBufferData(GL_ARRAY_BUFFER, shapeBuffer, GL_STATIC_DRAW);



        glEnableVertexAttribArray(0);
        glVertexAttribPointer(
            0, // id
            2, // how many items on coordinates ex. 0.5f, 0.5f
            GL_FLOAT, // data type
            false,  // false for raw numbers
             6 * Float.BYTES,  // how many bytes for next vertex items
            0   // where to start to read a byte '0' means 0 byte to read
        );
        glVertexAttribPointer(
                1,
                4,
                GL_FLOAT,
                false,
                6 * Float.BYTES,
                2 * Float.BYTES
        );

        // unbind the vertices for clearing up
        glBindBuffer(GL_ARRAY_BUFFER, 0);

        // also clears memory
        MemoryUtil.memFree(shapeBuffer);
    }
}
