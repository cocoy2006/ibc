package molab.util;

public class Status {
	
	public static enum Common {
		FAILURE(0), SUCCESS(1), NO(0), YES(1), CLOSE(0), OPEN(1), REMOVED(9);
		private int value;

		private Common(int value) {
			this.value = value;
		}

		public int getInt() {
			return value;
		}
	}
	
	public static enum DeviceState {
		CLOSED(0), UN_CONF(1), UN_AGENT(2), UN_OWNER(3), UNBOUND(4), BINDING(5), REMOVED(9);
		private int value;

		private DeviceState(int value) {
			this.value = value;
		}

		public int getInt() {
			return value;
		}
	}
	
	public static enum AuditState {
		NOT_PASS(0), ING(1), PAST(2), INIT(3);
		private int value;

		private AuditState(int value) {
			this.value = value;
		}

		public int getInt() {
			return value;
		}
	}
	
	public static enum UserSex {
		UNKNOWN(0), MALE(1), FEMALE(2);
		private int value;

		private UserSex(int value) {
			this.value = value;
		}

		public int getInt() {
			return value;
		}
	}
	
	public static enum PacketType {
		UNKNOWN(0), FIXED(1), RANDOM(2);
		private int value;

		private PacketType(int value) {
			this.value = value;
		}

		public int getInt() {
			return value;
		}
	}
	
	public static enum PacketState {
		CLOSED(0), UNBOUND(1), BINDING(2), REMOVED(9);
		private int value;

		private PacketState(int value) {
			this.value = value;
		}

		public int getInt() {
			return value;
		}
	}
	
}
