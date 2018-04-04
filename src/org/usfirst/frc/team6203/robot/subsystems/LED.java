package org.usfirst.frc.team6203.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team6203.robot.OI;
import org.usfirst.frc.team6203.robot.RobotMap;

import edu.wpi.first.wpilibj.I2C;

public class LED extends Subsystem {

	I2C p;

	public LED() {
		p = new I2C(I2C.Port.kOnboard, 9);
	}

	public void set(byte[] data) {
		p.transaction(data, data.length, new byte[0], 0);
	}

	public void initDefaultCommand() {
		pong_or_gg();
	}

	public byte getEmote() {
		int a = OI.emojiStick.getRawButton(RobotMap.emoji0) ? 1 : 0;
		int b = OI.emojiStick.getRawButton(RobotMap.emoji1) ? 1 : 0;
		int c = OI.emojiStick.getRawButton(RobotMap.emoji2) ? 1 : 0;
		int d = OI.emojiStick.getRawButton(RobotMap.emoji3) ? 1 : 0;

		return (byte) (8 * a + 4 * b + 2 * c + d);
	}

	public void emote() {
		byte x = getEmote();
		byte[] data = { 0, x };
		set(data);
	}

	public void glhf() {
		byte[] data = { 1 };
		set(data);
	}

	public void pong_or_gg() {
		byte[] data = { 2 };
		set(data);
	}
}
