package com.github.gohanchannel.pete;

import java.util.Collections;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Command extends JavaPlugin implements CommandExecutor{
	@Override
	public void onEnable() {
		getCommand("pete").setExecutor(this);
		/*
		 * config関連
		 */
		// 存在しない時の出力
		saveDefaultConfig();
		// 読み込み
	}

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
		//デバッグ用
		//sender.sendMessage("コマンドを受け付けました");
		//
		if(!(sender instanceof Player)) {
			System.out.print("プレイヤーから実行してください");
			return false;
		}
		FileConfiguration config = getConfig();
		//ArrayListの順番をシャッフルし、先頭を抽出
		Collections.shuffle(OnBlock.randomPlayer);
		Player result = OnBlock.randomPlayer.get(0);
		//ここに本番はTPに関することを書きこむ

		/*
		 * 落ちた時用にConfigに書き込む
		 */
		if (config.contains("Oni") == true ) {
			this.getConfig().set("Oni", null);
			config.set("Oni", result);
		    this.saveConfig();
		}else {
			getServer().broadcastMessage("エラーが発生しました#1001");
			getServer().broadcastMessage("サーバ管理者/管理者に問い合わせてください。");
		}
		//結果の公表
		getServer().broadcastMessage("§a今回の青鬼は"+result+"さんです。(~o~)");

		return false;
	}

	public void onDisable() {
		this.getConfig().set("Oni", null);
		System.out.println("seeyou");
	}

}
