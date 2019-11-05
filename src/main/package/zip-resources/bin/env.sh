JVM_ARGS="-Xms1G -Xmx1G -Xmn512m -XX:+DisableExplicitGC -XX:-UseGCOverheadLimit"
JVM_ARGS=${JVM_ARGS}" -Djava.net.preferIPv4Stack=true"