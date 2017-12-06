# -*- mode: ruby -*-
# vi: set ft=ruby :

Vagrant.configure(2) do |config|
  config.ssh.forward_agent = true
  config.vm.network "forwarded_port", guest: 8085, host: 8085

  config.vm.provider "virtualbox" do |vb|
    # Display the VirtualBox GUI when booting the machine
     vb.gui = false

     # Customize the amount of memory on the VM:
     vb.memory = "2048"
     vb.customize ["modifyvm", :id, "--vtxvpid", "on", "--vrde", "on"]
   end

  config.vm.provider "virtualbox" do |v, override|
    config.vm.box = "ubuntu/trusty64"
  end

  config.vm.provider "vmware_fusion" do |v, override|
    config.vm.box = "gracenote/trusty64"
    override.vm.box_url = "https://s3-us-west-2.amazonaws.com/vagrant-dev/gn-vmware-ubuntu-base.box"
  end

  $user_script = <<-SCRIPT
  cd /vagrant
  sudo apt-get install unzip
  sudo wget https://services.gradle.org/distributions/gradle-4.3.1-bin.zip
  sudo mkdir /opt/gradle
  sudo unzip -d /opt/gradle gradle-4.3.1-bin.zip

  wget --no-check-certificate -c --header "Cookie: oraclelicense=accept-securebackup-cookie" http://download.oracle.com/otn-pub/java/jdk/8u151-b12/e758a0de34e24606bca991d704f6dcbf/jdk-8u151-linux-x64.tar.gz
  sudo mkdir /usr/lib/jvm
  cd /usr/lib/jvm
  sudo tar -xvzf /vagrant/jdk-8u151-linux-x64.tar.gz

  cd /vagrant
  sudo rm gradle-4.3.1-bin.zip
  sudo rm jdk-8u151-linux-x64.tar.gz
  sudo cp environment /etc/environment
  source /etc/environment
  SCRIPT

  config.vm.provision "shell", inline: $user_script, privileged: false
end
