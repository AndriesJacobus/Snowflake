import java.util.Arrays;
import java.util.Random;

/**
 * Created by Jaco du Plooy
 */

public class SnowflakeHandler
{
    private String[][] canvas;

    public SnowflakeHandler (int len)
    {
        // Initialize canvas
        canvas = new String[len][len];
    }

    public void drawSnowflake (int size)
    {
        for (int i = 0; i < size * 2 + 1; i++)
        {
            // Draw vertical line
            if (i == 0 || i == size * 2)
            {
                canvas[i][size] = "@";
            }
            else
            {
                canvas[i][size] = "$";
            }

            // Draw horizontal line
            if (i == 0 || i == size * 2)
            {
                canvas[size][i] = "@";
            }
            else
            {
                canvas[size][i] = "$";
            }

        }

        System.out.println("Building your new unique snowflake...");
        printSnowflake(false);

        if (size > 1)
        {
            // Draw a unique pattern for each wing
            for (int i = 0; i < 4; i++)
            {
                drawUniquePattern((size / 2), size, size - 1);
                printSnowflake(false);
                rotate90();
            }

            printSnowflake(true);
        }
    }

    private void drawUniquePattern (int size, int xCenter, int yBase)
    {
        Random rand = new Random();
        // rand = rand.nextInt((max - min) + 1) + min;

        // Unique pattern's number of extra wings: rand between 0 and (size * 2)
        int numWings = rand.nextInt((size * 2) + 1);

        for (int i = 0; i < numWings; i++)
        {
            // Each wing has own unique size: rand between 1 and (size / 2)
            int currWingSize = rand.nextInt(((size * 2 - 1) - 1) + 1) + 1;

            if (currWingSize % 2 == 0)
            {
                // currWingSize is even, make it odd
                currWingSize--;
            }

            // Each wing has own unique position: rand between 1 and size
            int currWingPosition = rand.nextInt(((size * 2 - 1) - 1) + 1) + 1;

            for (int j = 0; j <= currWingSize; j++)
            {
                // Draw left wing
                if (canvas[yBase - currWingPosition][xCenter - j] == null)
                {
                    canvas[yBase - currWingPosition][xCenter - j] = "$";
                }
                else
                {
                    canvas[yBase - currWingPosition][xCenter - j] = "@";
                }

                // Draw right wing
                if (canvas[yBase - currWingPosition][xCenter + j] == null)
                {
                    canvas[yBase - currWingPosition][xCenter + j] = "$";
                }
                else
                {
                    canvas[yBase - currWingPosition][xCenter + j] = "@";
                }
            }
        }

    }

    private void rotate90 ()
    {
        String[][] rotated = new String[canvas.length][canvas.length];

        for (int i = 0; i < canvas.length; i++)
        {
            for (int j = 0; j < canvas.length; j++)
            {
                rotated[j][canvas.length - 1 - i] = canvas[i][j];
            }
        }

        canvas = rotated;
    }

    // Snowflake print animation
    public void printSnowflake (boolean rotating)
    {
        if (!rotating)
        {
            // Initial draw. Animate a more scrolling effect
            for (int i = 0; i < canvas.length; i++)
            {
                try
                {
                    Thread.sleep(16);       //60 fps
                    String print = Arrays.toString(canvas[i]);
                    System.out.println(print.substring(1, print.length() - 1).replaceAll(",", "").replaceAll("null", " "));
                    Thread.sleep(300);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }

            // Clear screen
            for (int j = 0; j < 25; j++)
            {
                try
                {
                    Thread.sleep(100);
                    System.out.println();
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
        else
        {
            // Rotating, thus make quick snaps between "frames"
            StringBuilder out = new StringBuilder("");
            for (int j = 0; j < 100; j++)
            {
                out.append("\n");
            }

            out.append("Your (rather) unique snowflake:\n\n");
            for (int i = 0; i < canvas.length; i++)
            {
                String print = Arrays.toString(canvas[i]);
                out.append(print.substring(1, print.length() - 1).replaceAll(",", "").replaceAll("null", " ") + "\n");
            }

            try
            {
//                Thread.sleep(16);
                System.out.println(out.toString());
                Thread.sleep(1000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }

            rotate90();
            printSnowflake(true);
        }
    }
}
