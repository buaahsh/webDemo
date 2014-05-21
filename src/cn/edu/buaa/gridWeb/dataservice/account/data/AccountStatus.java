/**
 * 
 */
package cn.edu.buaa.gridWeb.dataservice.account.data;



/**
 * @author caorongqiang
 * 账号的状态，3个数字
 */
public class AccountStatus {
	
		private int user_status=0;
		private int lock_status=0;
		private int map_status=0;
		
		

		
		public AccountStatus() {
			super();
		}
		
		
		
		/**
		 * @return the user_status
		 */
		public int getUser_status() {
			return user_status;
		}
		/**
		 * @param user_status the user_status to set
		 */
		public void setUser_status(int user_status) {
			this.user_status = user_status;
		}
		/**
		 * @return the lock_status
		 */
		public int getLock_status() {
			return lock_status;
		}
		/**
		 * @param lock_status the lock_status to set
		 */
		public void setLock_status(int lock_status) {
			this.lock_status = lock_status;
		}
		/**
		 * @return the map_status
		 */
		public int getMap_status() {
			return map_status;
		}
		/**
		 * @param map_status the map_status to set
		 */
		public void setMap_status(int map_status) {
			this.map_status = map_status;
		}

		
		
		
}
