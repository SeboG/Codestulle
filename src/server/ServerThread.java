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
	private BufferedWriter writer;

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
			if (clientSocket.equals(gameConni.playerA.getClientSocket()))
			{

				out = gameConni.playerB.getClientSocket().getOutputStream();
			}
			else
			{
				out = gameConni.playerA.getClientSocket().getOutputStream();
			}
			writer = new BufferedWriter(new OutputStreamWriter(out));
			InputStream in = clientSocket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			while (clientSocket.isConnected())
			{
				String msg = reader.readLine();
				if (msg != null)
				{
					if (msg.length() == 2)
					{
						empfangeKoords(msg);
					}
				}
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private void empfangeKoords(String koords)
	{
		int x = koords.charAt(0) - 97;
		int y = Integer.parseInt(koords.substring(1, 2)) - 1;
		/*
		 * An Spiel senden
		 */
		String antwortMsg = "";
		/*
		 * zurück bekommen, ergebnis zurückschicken
		 */
		try
		{
			writer.write(antwortMsg);
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
