package mains;

import services.IMoteurJeuService;



public class ThreadListener extends Thread {


		private IMoteurJeuService moteurJeu;

		public ThreadListener(IMoteurJeuService moteurJeu) {
			this.moteurJeu = moteurJeu;
		}
		
		@Override
		public void run()
		{
		//	new KeyListenerTester("toto", moteurJeu);
		}
}
