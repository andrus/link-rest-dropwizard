package org.objectstyle.linkrest.cms;

import org.apache.cayenne.access.dbsync.CreateIfNoSchemaStrategy;
import org.apache.cayenne.access.dbsync.SchemaUpdateStrategy;
import org.apache.cayenne.configuration.server.ServerRuntime;
import org.apache.cayenne.configuration.server.ServerRuntimeBuilder;
import org.objectstyle.linkrest.cms.derby.DerbyManager;
import org.objectstyle.linkrest.cms.resource.DomainResource;

import com.nhl.link.rest.runtime.LinkRestBuilder;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;

/**
 * A runnable DropWizard (http://www.dropwizard.io/) application. It uses
 * default Configuration object and no config YAML. Though you can easily add
 * your custom Configuration subclass initialized from YAML.
 */
public class DWApplication extends Application<Configuration> {

	private ServerRuntime cayenneRuntime;
	private DerbyManager derbyManager;

	public static void main(String[] args) throws Exception {
		new DWApplication().run(args);
	}

	@Override
	public String getName() {
		return "link-rest-dropwizard1";
	}

	@Override
	public void run(Configuration configuration, Environment environment) throws Exception {
		// init persistence layer...
		this.derbyManager = createDerby();
		this.cayenneRuntime = createCayenne(derbyManager.getUrl(), derbyManager.getDriver());

		// init and bootstrap LinkRest
		environment.jersey().register(LinkRestBuilder.build(cayenneRuntime));

		environment.jersey().register(DomainResource.class);
	}

	private static ServerRuntime createCayenne(String url, String driver) {

		return new ServerRuntimeBuilder().jdbcDriver(driver).url(url).addConfig("cayenne-project.xml")
				// this would ensure test schema creation on the first run
				.addModule(binder -> binder.bind(SchemaUpdateStrategy.class).to(CreateIfNoSchemaStrategy.class))
				.build();
	}

	private static DerbyManager createDerby() {
		return new DerbyManager();
	}
}
