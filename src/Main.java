import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class Main {

	public static void main(String[] args) throws IOException
	{
		//Pipes are mainly used for thread communication
		PipedInputStream input = new PipedInputStream();
		//input and output have to be linked with each other
		PipedOutputStream output = new PipedOutputStream(input);

		Thread thread1 = new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					while(true)
					{
						//write data in output
						output.write("thread1 writing ...".getBytes());
						Thread.sleep(5000);
					}
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				} 
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		});
		
		Thread thread2 = new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					byte[] buffer = new byte[1024];
					while(true)
					{
						//read data through input
						input.read(buffer);
						System.out.println(new String(buffer));
					}
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				} 
			}
		});
		
		thread1.start();
		thread2.start();
	}
}
