package fr.erdprt.samples.commons.remote.connection;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class TransportConfigurationMapParameters {

	public static Map<String, String> getMap(final URL hornetQConnectionUrl) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("host", hornetQConnectionUrl.getHost());
		map.put("port", String.valueOf(hornetQConnectionUrl.getPort()));
		return map;
	}

}
