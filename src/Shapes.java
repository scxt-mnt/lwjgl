import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.ARBVertexArrayObject.glBindVertexArray;
import static org.lwjgl.opengl.ARBVertexArrayObject.glGenVertexArrays;
import static org.lwjgl.opengl.GL15C.*;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;

public class Shapes {
    private int vboId;
    private int vaoId;
    private int vertexCounts;
    public Shapes(float[] vertices) {
        // generate id
        vaoId = glGenVertexArrays();
        vboId = glGenBuffers();
        vertexCounts = vertices.length/5;

        // bindings
        glBindVertexArray(vaoId);
        glBindBuffer(GL_ARRAY_BUFFER, vboId);

        // making storage
        FloatBuffer shapeBuffer = MemoryUtil.memAllocFloat(vertices.length);

        // store data
        shapeBuffer.put(vertices).flip();

        glBufferData(GL_ARRAY_BUFFER, shapeBuffer, GL_STATIC_DRAW);

        // vao Area
        glEnableVertexAttribArray(0);
        glVertexAttribPointer(
                0,
                2,
                GL_FLOAT,
                false,
                5 * Float.BYTES,
                0
        );
        glEnableVertexAttribArray(1);
        glVertexAttribPointer(
                1,
                3,
                GL_FLOAT,
                false,
                5 * Float.BYTES,
                2 * Float.BYTES

        );
        // clearing up

        MemoryUtil.memFree(shapeBuffer);

        glBindVertexArray(0);

        glBindBuffer(GL_ARRAY_BUFFER, 0);


    }
    public void render(){
        glBindVertexArray(vaoId);
        glDrawArrays(GL_TRIANGLES, 0, vertexCounts);
        glBindVertexArray(0);
    };
}
