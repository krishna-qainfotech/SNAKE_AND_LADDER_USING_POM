package Test.Java;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qainfotech.tap.training.snl.api.Board;
import com.qainfotech.tap.training.snl.api.GameInProgressException;
import com.qainfotech.tap.training.snl.api.InvalidTurnException;
import com.qainfotech.tap.training.snl.api.MaxPlayersReachedExeption;
import com.qainfotech.tap.training.snl.api.NoUserWithSuchUUIDException;
import com.qainfotech.tap.training.snl.api.PlayerExistsException;


public class LastTestCase {
	UUID uuid;
	JSONObject data;
	Board board;
	
	@BeforeMethod
	public void snl_test() throws FileNotFoundException, UnsupportedEncodingException, IOException {
		board=new Board();
	}
	
	
  @Test(expectedExceptions= {PlayerExistsException.class})
  public void Player_Exist() throws FileNotFoundException, UnsupportedEncodingException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption, IOException,NullPointerException,PlayerExistsException, GameInProgressException,
  FileNotFoundException, UnsupportedEncodingException,
  MaxPlayersReachedExeption, IOException {
	  
	  board.registerPlayer("Krishna");
	  //board.registerPlayer("Deepu");
	  board.registerPlayer("Krishna");
	  
  }
  
  
  @Test(expectedExceptions= {MaxPlayersReachedExeption.class})
  public void Max_Player() throws FileNotFoundException, UnsupportedEncodingException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption, IOException {
	  board.registerPlayer("Krishna");
	  board.registerPlayer("Sushma");
      board.registerPlayer("Parul");
	  board.registerPlayer("Deepu");
	  board.registerPlayer("Om prakash");
	  
  }
  
  @Test(expectedExceptions= {NoUserWithSuchUUIDException.class})
  public void Delete_UnregisterPlayer() throws FileNotFoundException, UnsupportedEncodingException, NoUserWithSuchUUIDException {
	  uuid = UUID.randomUUID();
	  board.deletePlayer(uuid);
  }
  
 /* @Test
  public void Delete_player() throws FileNotFoundException, UnsupportedEncodingException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption, IOException, NoUserWithSuchUUIDException {
	  board.registerPlayer("Krishna");
	  board.registerPlayer("Parul");
	  board.registerPlayer("Punit");
      JSONObject data=board.getData();
      JSONObject player=data.getJSONArray("player").getJSONObject(0);
      UUID playerID=(UUID)player.get("uuid");
      board.deletePlayer(playerID);
      Assert.assertEquals(2, data.getJSONArray("players").length());
      
  }*/
 @Test(expectedExceptions= {GameInProgressException.class})
  public void Game_In_progress_Exception() throws FileNotFoundException, UnsupportedEncodingException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption, IOException, InvalidTurnException {
	  board.registerPlayer("Krishna");
	  board.registerPlayer("Sushma");
	//  board.registerPlayer("Parul");
	//  board.registerPlayer("Deepu");
	  JSONObject data=board.getData();
	  JSONArray player=data.getJSONArray("players");
	  JSONObject player1=player.getJSONObject(0);
	  UUID uid=(UUID)player1.get("uuid");
	  board.rollDice(uid);
	  board.registerPlayer("Rohan");
	  
  }
 @Test(expectedExceptions= {InvalidTurnException.class})
 public void Invalid_Turn_exception() throws FileNotFoundException, UnsupportedEncodingException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption, IOException, InvalidTurnException {
	 JSONObject player1=board.registerPlayer("Krishna").getJSONObject(0);
	String uuid1=player1.getString("uuid");
	UUID playerUUid=UUID.fromString(uuid1);
	 JSONObject player2=board.registerPlayer("Parul").getJSONObject(1);
		String uuid2=player2.getString("uuid");
		UUID player2uuid=UUID.fromString(uuid2);
		board.rollDice(player2uuid);
 }
 
 
}
