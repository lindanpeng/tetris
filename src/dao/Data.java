package dao;

import java.util.List;

import dto.Player;

/*
 * 数据持久层次接口
 */
public interface Data {

public List<Player> loadData();
void saveData(Player players);
}
