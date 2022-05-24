package client.model.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

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
            return pList;
        } catch (Exception e) {
            throw e;
        }
    }

}
