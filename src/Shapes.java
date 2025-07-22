import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;


import static org.lwjgl.opengl.ARBVertexArrayObject.glBindVertexArray;
import static org.lwjgl.opengl.ARBVertexArrayObject.glGenVertexArrays;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11C.GL_FLOAT;

import static org.lwjgl.opengl.GL20.*;

public class Shapes {
    private int vertexCount;
    private int vboId;
    private int vaoId;


    public Shapes(float[] shape){

        // generate an id
        vboId = glGenBuffers();
        vaoId = glGenVertexArrays();

        // make an purpose
        glBindBuffer(GL_ARRAY_BUFFER, vboId);
        glBindVertexArray(vaoId);

        // make a storage
        FloatBuffer shapeBuffer = MemoryUtil.memAllocFloat(shape.length);
        // putting the data to buffer and flipping
        shapeBuffer.put(shape).flip();
        // uploading vartex to gpu
        glBufferData(GL_ARRAY_BUFFER, shapeBuffer, GL_STATIC_DRAW);

        // array index 0
        glEnableVertexAttribArray(0);
        glVertexAttribPointer(
                0,
                2,
                GL_FLOAT,
                false,
                24,
                0
        );
        glEnableVertexAttribArray(1);
        glVertexAttribPointer(
                1,
                4,
                    GL_FLOAT,
                    false,
                    24,
                    8
        );

        // unbind the vertices for clearing up
        glBindBuffer(GL_ARRAY_BUFFER, 0);

        // also clears memory
        MemoryUtil.memFree(shapeBuffer);

        vertexCount = shape.length/6;
    }

    public void render() {
        glBindVertexArray(vaoId);
        glDrawArrays(GL_TRIANGLES, 0, vertexCount);
        glBindVertexArray(0);
    }

}
