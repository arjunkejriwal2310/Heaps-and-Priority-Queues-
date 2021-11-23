public class performChecker
{
    public static void main(String[] args)
    {
        RunTimer timer = new RunTimer();

        int[] vals = new int[1000];

        for(int i = 0; i < 1000; i++)
        {
            vals[i] = 2*i + 23;
        }


        SimplePriorityQueue<Integer> queue1 = new HeapPriorityQueue<Integer>();
        SimplePriorityQueue<Integer> queue2 = new AVLPriorityQueue<Integer>();

        System.out.println(" ");
        System.out.println("----------------------------------------------------------");
        System.out.println(" ");

        for(int a = 0; a < 1000; a++)
        {
            queue2.insert(a, vals[a]);
        }

        for(int j = 0; j < 1000; j++)
        {
            timer.start();

            queue2.removeMin();

            if(j % 100 == 0)
            {
                System.out.println(timer.getElapsedNanos());
            }

            timer.stop();
        }

    }
}

