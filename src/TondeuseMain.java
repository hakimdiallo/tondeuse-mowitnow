import com.soul.utils.TondeuseHandler;
import java.io.IOException;

public class TondeuseMain {

  public static void main(String[] args) {
    if (args.length == 0) {
      System.out.println("File not found as argument.");
      return;
    }
    try {
      TondeuseHandler tondeuseHandler = new TondeuseHandler(args[0]);
      tondeuseHandler.initAndStart(5);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
