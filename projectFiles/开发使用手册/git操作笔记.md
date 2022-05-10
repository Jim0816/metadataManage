# git操作笔记

## 一、git简介

### 1、git的历史

​       Linux 内核开源项目有着为数众广的参与者。绝大多数的 Linux 内核维护工作都花在了提交补丁和保存归档的繁琐事务上（1991－2002年间）。到 2002 年，Linux系统已经发展了十年了，代码库之大让Linus很难继续通过手工方式管理了，于是整个项目组开始启用分布式版本控制系统 BitKeeper 来管理和维护代码。

到 2005 年的时候，开发 BitKeeper 的商业公司同 Linux 内核开源社区的合作关系结束，他们收回了免费使用 BitKeeper 的权力。这就迫使 Linux 开源社区（特别是 Linux的缔造者 Linus Torvalds ）不得不吸取教训，只有开发一套属于自己的版本控制系统才不至于重蹈覆辙。他们对新的系统订了若干目标：

• 速度

• 简单的设计

• 对非线性开发模式的强力支持（允许上千个并行开发的分支）

• 完全分布式

• 有能力高效管理类似 Linux 内核一样的超大规模项目（速度和数据量）

### 2、git是什么

- Git是一个开源的分布式版本控制系统，可以有效、高速的处理从很小到非常大的项目版本管理。

- Git 是 Linus Torvalds 为了帮助管理 Linux 内核开发而开发的一个开放源码的版本控制软件。
- Git与常用的版本控制工具 CVS, Subversion 等不同，它采用了分布式版本库的方式，不必服务器端软件支持。

### 3、git的特点

优点：

适合分布式开发，强调个体；

公共服务器压力和数据量都不会太大；

速度快、灵活；

任意两个开发者之间可以很容易的解决冲突；

离线工作。

缺点：

代码保密性差，一旦开发者把整个库克隆下来就可以完全公开所有代码和版本信息；

权限控制不友好；如果需要对开发者限制各种权限的建议使用SVN。

### 4、git与SVN的区别

SVN是**集中式版本控制系统**，而Git是**分布式版本控制系统**，Git与SVN的区别可参考[Git与SVN的区别](https://blog.csdn.net/ThinkWon/article/details/101449611)

## 二、工作流程

一般工作流程如下：

1. 从远程仓库中克隆 Git 资源作为本地仓库；
2. 从本地仓库中checkout代码然后进行代码修改；
3. 在提交本地仓库前先将代码提交到暂存区；
4. 提交修改，提交到本地仓库；本地仓库中保存修改的各个历史版本；
5. 在需要和团队成员共享代码时，可以将修改代码push到远程仓库。

git的工作流程图如下：

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9yYXcuZ2l0aHVidXNlcmNvbnRlbnQuY29tL0pvdXJXb24vaW1hZ2UvbWFzdGVyL0dpdCVFNyVBRSU4MCVFNCVCQiU4Qi9HaXQlMjAlRTclOUElODQlRTUlQjclQTUlRTQlQkQlOUMlRTYlQjUlODElRTclQTglOEIlRTUlOUIlQkUucG5n?x-oss-process=image/format,png)

## 三、git的几个核心概念

### 1、工作区、暂存区、版本库、远程仓库

Git和其他版本控制系统如SVN的一个不同之处就是有暂存区的概念。

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9yYXcuZ2l0aHVidXNlcmNvbnRlbnQuY29tL0pvdXJXb24vaW1hZ2UvbWFzdGVyL2dpdC9HaXQlRTUlQjclQTUlRTQlQkQlOUMlRTYlQjUlODElRTclQTglOEIucG5n?x-oss-process=image/format,png)

Workspace： 工作区，就是你平时存放项目代码的地方

Index / Stage： 暂存区，用于临时存放你的改动，事实上它只是一个文件，保存即将提交到文件列表信息

Repository： 仓库区（或版本库），就是安全存放数据的位置，这里面有你提交到所有版本的数据。其中HEAD指向最新放入仓库的版本

Remote： 远程仓库，托管代码的服务器，可以简单的认为是你项目组中的一台电脑用于远程数据交换

### 2、分支

​        每次的提交Git都把它们串成一条时间线，这条时间线就是一个分支。截止到目前，只有一条时间线，在Git里这个分支叫主分支，即master分支。HEAD指针严格来说不是指向提交，而是指向master，master才是指向提交的。

​       一开始的时候，master分支是一条线，Git用master指向最新的提交，再用HEAD指向master，就能确定当前分支，以及当前分支的提交点：

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9yYXcuZ2l0aHVidXNlcmNvbnRlbnQuY29tL0pvdXJXb24vaW1hZ2UvbWFzdGVyL0dpdCVFNyVBRSU4MCVFNCVCQiU4Qi8lRTUlODglODYlRTYlOTQlQUYxLnBuZw?x-oss-process=image/format,png)

每次提交，master分支都会向前移动一步，这样随着不断提交，master分支的线也越来越长。

当我们创建新的分支，例如dev时，Git新建了一个指针叫dev，指向master相同的提交，再把HEAD指向dev，就表示当前分支在dev上：

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9yYXcuZ2l0aHVidXNlcmNvbnRlbnQuY29tL0pvdXJXb24vaW1hZ2UvbWFzdGVyL0dpdCVFNyVBRSU4MCVFNCVCQiU4Qi8lRTUlODglODYlRTYlOTQlQUYyLnBuZw?x-oss-process=image/format,png)



Git创建一个分支很快，因为除了增加一个dev指针，改改HEAD的指向，工作区的文件都没有任何变化！

不过切换到了dev分，对工作区的修改和提交就是针对dev分支了，比如新提交一次后，dev指针往前移动一步，而master指针不变：![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9yYXcuZ2l0aHVidXNlcmNvbnRlbnQuY29tL0pvdXJXb24vaW1hZ2UvbWFzdGVyL0dpdCVFNyVBRSU4MCVFNCVCQiU4Qi8lRTUlODglODYlRTYlOTQlQUYzLnBuZw?x-oss-process=image/format,png)

假如我们在dev上的工作完成了，就可以把dev合并到master上。Git怎么合并呢？最简单的方法，就是直接把master指向dev的当前提交，就完成了合并：

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9yYXcuZ2l0aHVidXNlcmNvbnRlbnQuY29tL0pvdXJXb24vaW1hZ2UvbWFzdGVyL0dpdCVFNyVBRSU4MCVFNCVCQiU4Qi8lRTUlODglODYlRTYlOTQlQUY0LnBuZw?x-oss-process=image/format,png)

所以Git合并分支也很快！就改改指针，工作区内容也不变！

合并完分支后，甚至可以删除dev分支。删除dev分支就是把dev指针给删掉，删掉后就剩下了一条master分支：

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9yYXcuZ2l0aHVidXNlcmNvbnRlbnQuY29tL0pvdXJXb24vaW1hZ2UvbWFzdGVyL0dpdCVFNyVBRSU4MCVFNCVCQiU4Qi8lRTUlODglODYlRTYlOTQlQUY1LnBuZw?x-oss-process=image/format,png)



### 3、远程仓库

​       本质和本地仓库无异，只是这个仓库①不在本地②大家可能都知道③需要将代码共享到远程仓库④可以被其他人克隆同步代码等。纯粹为了7x24小时开机并交换大家的修改。而业界最富盛名的远程仓库则为github；就是一个免费的托管开源代码的远程仓库，它上面存放了非常多的开源组织、个人、企业等的开放源码库，任何人都可以从上面获取源码。但是对于某些视源代码如生命的商业公司来说，既不想公开源代码，又舍不得给GitHub交保护费，那就只能自己搭建一台Git服务器作为私有仓库使用。

## 四、git冲突与解决方法

### 1、git冲突

​        简单来说就是本地修改的文件和目标远程库的同一个文件都有修改。这时无论是pull丶push丶merge时都会产生冲突。

git冲突情况举例：

1. 不同分支下的merge

   比如在进行分之合并时，我们在本地修改了了a文件并把a文件的修改push到了test分支下，接着我们切换到master分支下将test分支上的修改合并到当前master分支下时，如果master分支下的awenjain也有修改的话，这时在进行merge会产生冲突。  

2. 同一个分支下的pull或push

   比如在同一个分支下，对本地的a文件作出了修改，此时我们在进行pull或push时如果远程分支下的a文件也有修改，那么代表本地和远程分支的代码时不同步的，此时也会引起冲突。

### 2、冲突解决方法

​       要想不产生冲突的习惯是：修改文件之前先git pull,获取远程最新的代码，同步完了之后再对代码进行修改；亦或者事先和同时操作代码的同学/同事沟通，这样可以大概率的避免冲突的产生。

- 方法一（推荐使用）：


git pull 出现冲突后丢弃本地冲突文件修改，采用远程文件覆盖本地文件git checkout [文件路径]

例：

```
git checkout test/src/main/resources/spring-shiro.xml
```

- 方法二：


git pull 出现冲突后可以暂存本地修改git stash ,然后git pull 更新代码，git stash list 可查看暂存记录列表，释放本地暂存 git stash apply stash@{0} ，出现冲突文件，找到并解决，然后可以提交git add . 加入索引库，然后本地提交git commit -m '注释' 最后git push到远程。

## 五、常用的git命令

### 1、配置用户名和邮箱

```shell
$ git --version   # 查看git的版本信息
$ git config --global user.name   # 获取当前登录的用户
$ git config --global user.email  # 获取当前登录用户的邮箱
# Git的设置文件为.gitconfig，它可以在用户主目录下（全局配置），也可以在项目目录下（项目配置）。
# 显示当前的Git配置
$ git config --list
# 编辑Git配置文件
$ git config -e [--global]
# 设置提交代码时的用户信息
$ git config [--global] user.name "[name]"
$ git config [--global] user.email "[email address]"

```

### 2、将自己的代码推送到远程仓库正确流程

```shell
1. git init # 初始化仓库
2. git add .(文件name) # 添加文件到暂存区
3. git commit -m "first commit" # 添加文件到本地仓库并提交描述信息
4. git remote add origin 远程仓库地址 # 链接远程仓库，创建主分支
5. git pull origin master --allow-unrelated-histories # 把本地仓库的变化连接到远程仓库主分支
6. git push -u origin master # 把本地仓库的文件推送到远程仓库
```

### 3、新建本地仓库并将远程项目下载

```shell
# 创建一个文件夹
$ mkdir GitRepositories    # 创建文件夹GitRepositories
$ cd GitRepositories       # 切换到GitRepositories目录下
# 在当前目录新建一个Git代码库
$ git init
# 新建一个目录，将其初始化为Git代码库
$ git init [project-name]
# 下载一个项目和它的整个代码历史
$ git clone [url]
```

### 4、添加/删除文件到暂存区

```shell
# 添加指定文件到暂存区
$ git add [file1][file2] ...
# 添加指定目录到暂存区，包括子目录
$ git add [dir]
# 添加当前目录的所有文件到暂存区
$ git add .
# 添加每个变化前，都会要求确认
# 对于同一个文件的多处变化，可以实现分次提交
$ git add -p
# 删除工作区文件，并且将这次删除放入暂存区
$ git rm [file1] [file2] ...
# 停止追踪指定文件，但该文件会保留在工作区
$ git rm --cached [file]
# 改名文件，并且将这个改名放入暂存区
$ git mv [file-original] [file-renamed]
```

### 5、暂存区代码提交到本地仓库

```shell
# 提交暂存区到仓库区
$ git commit -m [message]
# 提交暂存区的指定文件到仓库区
$ git commit [file1] [file2] ... -m [message]
# 提交工作区自上次commit之后的变化，直接到仓库区
$ git commit -a
# 提交时显示所有diff信息
$ git commit -v
# 使用一次新的commit，替代上一次提交
# 如果代码没有任何新变化，则用来改写上一次commit的提交信息
$ git commit --amend -m [message]
# 重做上一次commit，并包括指定文件的新变化
$ git commit --amend [file1] [file2] ...
```

### 6、分支操作

```shell
# 列出所有本地分支
$ git branch
# 列出所有远程分支
$ git branch -r
# 列出所有本地分支和远程分支
$ git branch -a
# 新建一个分支，但依然停留在当前分支
$ git branch [branch-name]
# 新建一个分支，并切换到该分支
$ git checkout -b [branch]
# 新建一个分支，指向指定commit
$ git branch [branch] [commit]
# 新建一个分支，与指定的远程分支建立追踪关系
$ git branch --track [branch] [remote-branch]
# 切换到指定分支，并更新工作区
$ git checkout [branch-name]
# 切换到上一个分支
$ git checkout -
# 建立追踪关系，在现有分支与指定的远程分支之间
$ git branch --set-upstream [branch] [remote-branch]
# 合并指定分支到当前分支
$ git merge [branch]
# 选择一个commit，合并进当前分支
$ git cherry-pick [commit]
# 删除分支
$ git branch -d [branch-name]
# 删除远程分支
$ git push origin --delete [branch-name]
$ git branch -dr [remote/branch]
```

### 7、查看信息

```shell
# 查看目录
$ ls -al	或者$ ll
# 查看仓库状态，显示有变更的文件
$ git status
# 显示当前分支的版本历史
$ git log
# 显示commit历史，以及每次commit发生变更的文件
$ git log --stat
# 搜索提交历史，根据关键词
$ git log -S [keyword]
# 显示某个commit之后的所有变动，每个commit占据一行
$ git log [tag] HEAD --pretty=format:%s
# 显示某个commit之后的所有变动，其"提交说明"必须符合搜索条件
$ git log [tag] HEAD --grep feature
# 显示某个文件的版本历史，包括文件改名
$ git log --follow [file]
$ git whatchanged [file]
# 显示指定文件相关的每一次diff
$ git log -p [file]
# 显示过去5次提交
$ git log -5 --pretty --oneline
# 显示所有提交过的用户，按提交次数排序
$ git shortlog -sn
# 显示指定文件是什么人在什么时间修改过
$ git blame [file]
# 显示暂存区和工作区的差异
$ git diff
# 显示暂存区和上一个commit的差异
$ git diff --cached [file]
# 显示工作区与当前分支最新commit之间的差异
$ git diff HEAD
# 显示两次提交之间的差异
$ git diff [first-branch]...[second-branch]
# 显示今天你写了多少行代码
$ git diff --shortstat "@{0 day ago}"
# 显示某次提交的元数据和内容变化
$ git show [commit]
# 显示某次提交发生变化的文件
$ git show --name-only [commit]
# 显示某次提交时，某个文件的内容
$ git show [commit]:[filename]
# 显示当前分支的最近几次提交
$ git reflog
```

### 8、远程同步

```shell
# 下载远程仓库的所有变动
$ git fetch [remote]
# 显示所有远程仓库
$ git remote -v
# 显示某个远程仓库的信息
$ git remote show [remote]
# 增加一个新的远程仓库，并命名
$ git remote add [shortname] [url]
# 取回远程仓库的变化，并与本地分支合并
$ git pull [remote] [branch]
# 上传本地指定分支到远程仓库
$ git push [remote] [branch]
# 强行推送当前分支到远程仓库，即使有冲突
$ git push [remote] --force
# 推送所有分支到远程仓库
$ git push [remote] --all
```

### 9、撤销

```shell
# 恢复暂存区的指定文件到工作区
$ git checkout [file]
# 恢复某个commit的指定文件到暂存区和工作区
$ git checkout [commit] [file]
# 恢复暂存区的所有文件到工作区
$ git checkout .
# 重置暂存区的指定文件，与上一次commit保持一致，但工作区不变
$ git reset [file]
# 重置暂存区与工作区，与上一次commit保持一致
$ git reset --hard
# 重置当前分支的指针为指定commit，同时重置暂存区，但工作区不变
$ git reset [commit]
# 重置当前分支的HEAD为指定commit，同时重置暂存区和工作区，与指定commit一致
$ git reset --hard [commit]
# 重置当前HEAD为指定commit，但保持暂存区和工作区不变
$ git reset --keep [commit]
# 新建一个commit，用来撤销指定commit
# 后者的所有变化都将被前者抵消，并且应用到当前分支
$ git revert [commit]
# 暂时将未提交的变化移除，稍后再移入
$ git stash
$ git stash pop

```

### 10、其他

```shell
# 从当前目录的所有文件中查找文本内容：
$ git grep "Hello"
# 在某一版本中搜索文本：
$ git grep "Hello" v2.5
# 生成一个可供发布的压缩包
$ git archive

```

### 11、git命令速查表

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9yYXcuZ2l0aHVidXNlcmNvbnRlbnQuY29tL0pvdXJXb24vaW1hZ2UvbWFzdGVyL2dpdC9HaXQlRTUlQjglQjglRTclOTQlQTglRTUlOTElQkQlRTQlQkIlQTQlRTklODAlOUYlRTYlOUYlQTUlRTglQTElQTguanBn?x-oss-process=image/format,png)

