package net.a.g.ejb;

import java.util.Enumeration;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CompositeAsyncAuditLogReceiver implements MessageListener {

	private static final Logger logger = LoggerFactory.getLogger(CompositeAsyncAuditLogReceiver.class);

	public CompositeAsyncAuditLogReceiver() {
	}

	@Override
	public void onMessage(Message message) {

		try {
			logger.info("Audit log message received {}", message.getBody(String.class));

			Enumeration<String> enum_ = message.getPropertyNames();

			while (enum_.hasMoreElements()) {
				String key = enum_.nextElement();
				logger.info(key + " : " + message.getStringProperty(key));
			}

		} catch (JMSException e) {
			logger.error("Unexpected JMS exception while processing audit log message", e);
		}
	}

}
