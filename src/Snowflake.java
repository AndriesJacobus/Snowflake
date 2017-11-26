/**
 * @author:     Jaco du Plooy
 */

public class Snowflake
{
    private int max = 15;
    public static void main(String[] args)
    {
        Snowflake driver = new Snowflake();

        if (args.length == 0)
        {
            System.out.println("Please enter the length of the snowflake as the first pipeline argument." +
                    "\n\nExample:\n javac Snowflake.java; java Snowflake 15\n\n" +
                    "NOTE: the max is " + driver.max + ". Any length larger than this will be reduced.");
            System.exit(0);
        }

        try
        {
            int len = Integer.parseInt(args[0]);
            if (len > driver.max)
            {
                len = driver.max;
            }

            // All is good, make snowflake
            driver.generateSnowflake(len);
        }
        catch (NumberFormatException e)
        {
            System.out.println("The pipeline argument you provided is not an integer." +
                    "\n\nPlease make sure you enter an integer.\n" +
                    "Example:\n javac Snowflake.java; java Snowflake 15\n\n" +
                    "NOTE: the max is " + driver.max + ". Any length larger than this will be reduced.");
            System.exit(0);
        }
    }

    private void generateSnowflake (int len)
    {
        SnowflakeHandler handler = new SnowflakeHandler(len);
    }

    private class SnowflakeHandler
    {
        private String[][] canvas;

        public SnowflakeHandler (int len)
        {
            canvas = new String[len][len];
        }
    }
}
