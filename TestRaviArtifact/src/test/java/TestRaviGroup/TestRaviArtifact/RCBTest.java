package TestRaviGroup.TestRaviArtifact;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class RCBTest {

	@Test
	public static void ForeignPlayers() {
		System.out.println("******************************ForeignPlayers Test*******************************************");
		JsonPath js = new JsonPath(RCBJsonPayLoad.JsonBody());
		int NumberOfPlayers = js.getInt("player.size()");
		System.out.println("Number of players in " + js.get("name") + ": " + NumberOfPlayers);
		int ExpectedForeignPlayers = 4;
		int ActualForeignPlayers = 0;

		for (int i = 0; i < NumberOfPlayers; i++) {
			String t = js.getString("player[" + i + "].country");
			String t2 = js.getString("player[" + i + "].name");
			if (t.equals("India")) {
				// System.out.println(t2 + " Is not a foreign player");

			} else {
				ActualForeignPlayers++;
				System.out.println(t2 + " is a Foreign Player and belongs to " + t);
			}
		}

		Assert.assertEquals(ExpectedForeignPlayers, ActualForeignPlayers);
		System.out.println(js.get("name") + " has exactly " + ActualForeignPlayers + " Foreign players");

	}

	@Test
	public static void WicketKeepers() {
		System.out.println("******************************Wicket-Keepers Test*******************************************");
		JsonPath js = new JsonPath(RCBJsonPayLoad.JsonBody());
		int NumberOfPlayers = js.getInt("player.size()");
		// System.out.println(NumberOfPlayers);

		int NumOfWicketKeepers = 0;

		for (int i = 0; i < NumberOfPlayers; i++) {
			String t = js.getString("player[" + i + "].role");
			String t2 = js.getString("player[" + i + "].name");
			if (t.equals("Wicket-keeper")) {
				System.out.println(t2 + " Is a Wicket Keeper");
				NumOfWicketKeepers++;
			} else {

				// System.out.println(t2 + " Is not a Wicket Keeper " );
			}
		}

		if (NumOfWicketKeepers >= 1) {
			System.out.println(js.get("name") + " has atleast 1 Wicket keeper");
		}
		else
		{
			System.out.println(js.get("name") + " should have atleast 1 Wicket-Keeper");
		}

	}

}
