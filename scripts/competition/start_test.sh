##Testing for animations

mkdir -p log
today=`date +%Y-%m-%d-%H-%M-%S`
for ((i=6; i<=10; i++)); do
	java -cp "lib/*" magma.robots.RoboCupClient --teamname=Blue --factory=Nao --playerid=$i --server=$1 1>log/outAndError$today.log 2>&1 &
	sleep 3s
done

for ((i=6; i<=10; i++)); do
	java -cp "lib/*" magma.robots.RoboCupClient --teamname=Red --factory=Nao --playerid=$i --server=$1 1>log/outAndError$today.log 2>&1 &
	sleep 3s
done