. /etc/environment

# Compile tests+units
make clean && make units ARCH=faulty

#Run tests
./test/units.sh

make clean && make test
./test/autotest.sh
