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
  SCRIPT

  config.vm.provision "shell", inline: $user_script, privileged: false
end
