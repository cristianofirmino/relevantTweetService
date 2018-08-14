docker image build . --tag relevant-tservice:1.0.0
docker run -it -p 9999:80 relevant-tservice:1.0.0