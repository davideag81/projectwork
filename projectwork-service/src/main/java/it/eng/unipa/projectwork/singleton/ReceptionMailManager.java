package it.eng.unipa.projectwork.singleton;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Schedules;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import it.eng.unipa.projectwork.email.Message;
import it.eng.unipa.projectwork.email.Message.TYPE;
import it.eng.unipa.projectwork.email.ReceptionMail;
import it.eng.unipa.projectwork.email.exception.MailNotReceptionException;
import it.eng.unipa.projectwork.model.Auction;
import it.eng.unipa.projectwork.service.AuctionService;

@Singleton
@Startup
public class ReceptionMailManager {


	@EJB
	AuctionService auctionService;


	@EJB
	ReceptionMail ReceptionMail;

	/**
	 * 
	@Timeout
	public void timeout(Timer timer) {
	    System.out.println("TimerBean: timeout occurred");
	}
	 * @param timer
	 */

	@Schedules ({
		/*@Schedule(minute="0",hour="*",persistent=false)
		/*,
	    @Schedule(dayOfMonth="Last")
	    @Schedule(dayOfWeek="Fri", hour="23")
		 */
		@Schedule(minute="0/5", persistent=false)	/// Controlla nuove mail ogni 5 minuti

		
	})
	public void receptionMail() {
		
		//checkMail();
	}
	/*public void sendMail(){
		List<Auction> l = auctionService.loadActiveAuctions((a)->a);
		String today = new SimpleDateFormat("dd/MM/yyyy hh:mm").format(new Date());
		String subject = "Active auctions now: "+today;
		if(!l.isEmpty()){
			String body = body(l);
			try {
				sendMail.sendMailAllUser(new Message(subject, body,TYPE.HTML));
			} catch (MailNotSendException e) {

				e.printStackTrace();
			}
		}
	}
*/

	private String body(List<Auction> as){
		StringBuilder sb = new StringBuilder("<table>");
		sb.append("<tr><th>").append("title").append("</th>").append("<th>").append("description").append("</th></tr>");
		for(Auction a : as){
			sb.append("<tr><th>").append(a.getTitle()).append("</th>").append("<th>").append(a.getDescription()).append("</th></tr>");

		}
		sb.append("</table>");
		return sb.toString();
	}
}

