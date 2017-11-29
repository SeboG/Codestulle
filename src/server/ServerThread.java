package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ServerThread extends Thread
{
	private Socket clientSocket;
	private ClientConnection gameConni;

	public ServerThread(Socket clientSocket)
	{
		this.clientSocket = clientSocket;
	}

	@Override
	public void run()
	{
		try
		{
			OutputStream out;
			if(clientSocket.equals(gameConni.playerA.getClientSocket())) {
				
				out = gameConni.playerB.getClientSocket().getOutputStream();
			}
			else {
				out = gameConni.playerA.getClientSocket().getOutputStream();
			}
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
			InputStream in = clientSocket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			while (clientSocket.isConnected())
			{
				String msg = reader.readLine();
				if (msg != null)
				{
					writer.write(msg);
					writer.newLine();
					writer.flush();
				}
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public ClientConnection getGameConni()
	{
		return gameConni;
	}

	public void setGameConni(ClientConnection gameConni)
	{
		this.gameConni = gameConni;
	}

	public Socket getClientSocket()
	{
		return clientSocket;
	}
}
