import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class SocketSimples{
    public static void main( String[] args )
    {
        //Define a porta padrao
        int porta = 80;
        //Define o caminho padrao
        String path = "/";
        //Leitura da URL
        System.out.println("Digite a url:\n");
        Scanner ler = new Scanner(System.in);
        String server = ler.nextLine();

        if(server == null || server.isEmpty()){
            System.out.println("Digite uma URL!");
        }
        //Utilizar para excucao em linha passando argumentos
        //String server = args[ 0 ];

        System.out.println( "Loading contents of URL: " + server + "usando a porta " + porta);

        try
        {
            // Conectando ao servidor
            Socket socket = new Socket( server, porta );

            // Crie fluxos de entrada e saída para ler e gravar no servidor
            PrintStream out = new PrintStream( socket.getOutputStream() );
            BufferedReader in = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );

            // Definindo o metodo GET /
            out.println( "GET " + path);
            out.println();

            // Ler dados do servidor até terminarmos de ler o documento
            String line = in.readLine();
            while( line != null )
            {
                System.out.println( line );
                line = in.readLine();
            }

            // Fechando a conexao
            in.close();
            out.close();
            socket.close();
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
    }
}