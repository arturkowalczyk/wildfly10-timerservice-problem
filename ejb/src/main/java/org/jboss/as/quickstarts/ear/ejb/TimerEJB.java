package org.jboss.as.quickstarts.ear.ejb;

import java.util.Date;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;

@Singleton
@Startup
public class TimerEJB {

    private Logger logger = Logger.getLogger(TimerEJB.class.getName());

    @Resource
    private TimerService timerService;

    @PostConstruct
    private void init()
    {
        timerService.createIntervalTimer( new Date(), 5000, new TimerConfig( "Test timer", false ) );
    }

    @Timeout
    private void task()
    {
        logger.info("Time: " + System.currentTimeMillis());
    }

}
