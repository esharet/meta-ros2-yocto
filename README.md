# meta-ros2-yocto
how a per project yocto meta project should look like, and know hows

## training and resources 
https://bootlin.com/training/yocto/

reading the course slides and doing it's labs 

the mega-manual is helpful and very but very long, it is a good place to understand specific configurations and variables: 
https://www.yoctoproject.org/docs/current/mega-manual/mega-manual.html

## moving to internal environment
cloning all the outside repos and pushing inside : 

```
git clone --mirror <open_source_repo>
```
move the folder to the internal environment.
in the internal environment create on the server a repo with the same name and then: 
```
git init --bare 
git remote set-url 
git push --mirror <open_source_repo>
```
in the internal environment there will be a problem with the remotes urls, 
in order to update those follow this [tutorial](http://pa1gitsolutions.blogspot.com/2015/07/changing-git-submodules-urlbranch-to.html)
for each submodule: 
```
git config --file-.gitmodules submodule.SUBMODULENAME.url URL 
```
at the end: 
```
git submodule sync 
git submodule update --init --recursive --remote 
git pull --recurse-submodules 
```
**do this recursively from the bottom of the submodule tree to the top**

(in order to reproduce the internal environment outside - do the same in the outside environment.)  

downloading all the sources: 
```bitbake world --runall=fetch -k```

configure site.conf.sample as in this project 

## creating custom template 
https://www.yoctoproject.org/docs/current/mega-manual/mega-manual.html#creating-a-custom-template-configuration-directory
 
setenv should be as oe-init-env but use meta-project/project-template to create the build directory customized per-project

## flashing 
after building the image: 
in the build/tmp/deploy/images/name-of-machine/ directory (where all the created images are stored)

```
mkdir final
cd final 
tar -xaf ../correct-image.tgz
sudo ./doflash.sh (after inserting jetson to recovery mode)
```
in flashvars file there should be a BOARDID line, if there's not add it according to the corresponding boardid (for devkit BOARDID=3489)

## automation 
automate ci workflow to bitbake world at night 

## adding custom packages (ros2, python, cpp)
for python packages - 
inherit setuptools 
for cpp packages - 
inherit cmake 

write setup.py and cmakelists.txt correctly and build add to your recipe the files of setup.py / cmakelists and the src repo and it should do the rest. 

the required packages doesn't work well when parsing the setup.py file so it should be added in the DEPENDS variable in the recipe. 

ros2 packages also could be installed - with little changes to ros2-example-package recipe and make appropriate changes to DEPENDS and RDEPENDS variables.

## important know-hows and configurations 
read those parameters on the mega-manual    
BB_GENERATE_MIRROR_TARBALLS = "1" 
LICENSE_FLAGS_WHITELIST="commercial"

DEPENDS varibale - build depend  
RDEPENDS variable - run depend 

in bbappend files - use % at the end of the file name to append to all versions of this recipe 

add project files to image -
create a project layer and in it add a recipe with: 
do_install_append () { 
    ... (read on the internet)
} 

if some library isn't being fetched - try updating the ca-certificates:
``` 
sudo apt update ; sudo apt-get install apt-transport-https ca-certificates -y ; sudo update-ca-certificates
```
sudo apt update ; sudo apt-get install apt-transport-https ca-certificates -y ; sudo update-ca-certificates

save disk space: 
in local.conf add this line: 
INHERIT += "rm_work" 

if you want a given image to not take this effect: 
RM_WORK_EXCLUDE += "core-image-minimal"  
## remaining questions 
1. creating spacely machine 
2. RTI 
3. the difference between dev image and field image (in that context - read about devtool)

## steps to continue: 
1. creating spacely machine outside 
2. creating inside downloads repo 
3. ros2 installation outside 
4.  