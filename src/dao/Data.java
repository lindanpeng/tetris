package dao;

import java.util.List;

import dto.Player;

/*
 * ���ݳ־ò�νӿ�
 */
public interface Data {

public List<Player> loadData();
void saveData(Player players);
}
