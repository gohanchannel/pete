package com.github.gohanchannel.pete;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class OnBlock extends JavaPlugin implements Listener {
	//参考 http://www.jias.jp/blog/?9
	static ArrayList<Player> randomPlayer = new ArrayList<>();
	/*
	 * 起動時処理
	 */
	public void onEnable() {
		//randomPlayer用のArrayList
		//イベントリスナの登録
		getServer().getPluginManager().registerEvents(this, this);
	}
	/*
	 * プレイヤー移動の検知
	 * @param e
	 */
	@EventHandler(ignoreCancelled = true)
	public void onPlayerMoveEvent(PlayerMoveEvent e) {
	//地面に立っているか否か
		if(((Entity)e.getPlayer()).isOnGround()) {
			//2段のシルバー石検知
			Location loc=e.getTo().clone();
			Location loc2=e.getTo().clone();
			loc.add(0,-0.1,0);
			loc2.add(0,-1.1,0);

			if(loc.getBlock().getType().equals(Material.MONSTER_EGGS) && loc2.getBlock().getType().equals(Material.MONSTER_EGGS)) {
				//シルバー石検知で実行される内容
				randomPlayer.add(e.getPlayer());
				//デバッグ用
				//getServer().broadcastMessage("§9"+e.getPlayer().getName()+" is on monsteregg.");
			}else {
				getServer().broadcastMessage("エラーが発生しました#2001");
				getServer().broadcastMessage("サーバ管理者/管理者に問い合わせてください。");
			}
		}
	}
	/*
	 * 終了時処理
	 */
	public void onDisable() {

	}

}
