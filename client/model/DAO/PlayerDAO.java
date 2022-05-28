package client.model.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Comparator;
import client.model.utils.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PlayerDAO {

    private static ObservableList<Player> getPlayersList(ResultSet rSet) {
        ObservableList<Player> pList = FXCollections.observableArrayList();
        try {
            while(rSet.next()) {
                Player player = new Player(rSet.getString("NICKNAME"), rSet.getInt("SCORE"), rSet.getInt("ID"));
                pList.add(player);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pList;
    }

    public static ObservableList<Player> getAll() throws Exception {
        String query = "SELECT NICKNAME, SCORE, ID FROM RATING;";
        try {
            ResultSet rSet = DBUtil.dbExecuteQuery(query);
            ObservableList<Player> pList = getPlayersList(rSet);
            pList.sort(new Comparator<Player>() {
                @Override
                public int compare(Player o1, Player o2) {
                    if (o1.getScore() == o2.getScore()) return 0;
                    else if (o1.getScore() > o2.getScore()) return -1;
                    else return 1;
                }
            });
            return pList;
        } catch (Exception e) {
            throw e;
        }
    }

    public static void insert(int score, String nickname) {
        try {
            ObservableList<Player> pList = getAll();
            if (pList.get(9).getScore() < score) {
                String query = "INSERT INTO RATING (NICKNAME, SCORE) VALUES ('" + nickname + "', " + Integer.toString(score) + ");";
                DBUtil.dbExecuteUpdate(query);
                pList = getAll();
                int id = pList.get(10).getId();
                query = "DELETE FROM RATING\nWHERE ID = " + id + ";";
                DBUtil.dbExecuteUpdate(query);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
