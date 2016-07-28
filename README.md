wildfly10-timerservice-problem 
==========================
An example project demonstrating the problem with TimerService in Singleton Deployment. The example was created based of WildFly's quickstart/ejb-in-ear.

Cluster Configuration
------------------------------
The cluster consists of two WildFly instances. The instances should be started with the usage of the following parameters.

**Master WildFly**
> standalone.bat -c standalone-full-ha.xml -Djboss.node.name=master

**Slave WildFly**
> standalone.bat -c standalone-full-ha.xml -Djboss.node.name=slave -Djboss.socket.binding.port-offset=100

Singleton Policy
-----------------------
The application also uses a custom singleton policy with a preference for a name. The name-preferences cause that the master is the most important instance in the cluster. When the master WildFly is connected to the cluster the test application is running on this node.

    <singleton-policy name="scada-singleton" cache-container="server">
      <simple-election-policy>
        <name-preferences>master</name-preferences>
      </simple-election-policy>
    </singleton-policy>

The policy must be defined in standalone-full-ha.xml configuration file in singleton subsystem.

