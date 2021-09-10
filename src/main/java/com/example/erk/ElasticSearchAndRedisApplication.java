package com.example.erk;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableCaching
@SpringBootApplication
public class ElasticSearchAndRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElasticSearchAndRedisApplication.class, args);

		String encoded = "{&quot;groupId&quot;:&quot;60228201e85b8300067656ff&quot;,&quot;schemaId&quot;:&quot;6013fca9a5a43c000638b0c0&quot;,&quot;tenantId&quot;:&quot;59ce808c99298e1e06660c09&quot;,&quot;groupDefinition&quot;:&quot;SELECT t0.&quot;id&quot; AS id FROM t_6013fca9a5a43c000638b0c0_t t0 WHERE (t0.&quot;entity.status&quot; LIKE 'running')&quot;\n"
				+ "\n";
		String decoded = StringEscapeUtils.unescapeHtml3(encoded);
		System.out.println(decoded);
	}

}
