# Questions 

## Q1 使用 GCLogAnalysis.java 自己演练一遍串行/并行/CMS/G1的案例。

### Environment

* OS

```text
CPU: Intel(R) Core(TM) i7-5557U CPU @ 3.10GHz (logical cpu core number: 4)
Memory: 16GB
```

* Java

```shell
$ java -version
openjdk version "1.8.0_242"
OpenJDK Runtime Environment (AdoptOpenJDK)(build 1.8.0_242-b08)
OpenJDK 64-Bit Server VM (AdoptOpenJDK)(build 25.242-b08, mixed mode)
```

### Serial GC 

```shell
$ java -XX:+UseSerialGC -Xms512m -Xmx512m -Xloggc:gc.demo.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
正在执行...
执行结束!共生成对象次数:7400
```

```text
# File: gc.demo.log
OpenJDK 64-Bit Server VM (25.242-b08) for bsd-amd64 JRE (1.8.0_242-b08), built on Jan 19 2020 06:43:11 by "jenkins" with gcc 4.2.1 (Based on Apple Inc. build 5658) (LLVM build 2336.11.00)
Memory: 4k page, physical 16777216k(3777032k free)

/proc/meminfo:

CommandLine flags: -XX:InitialHeapSize=536870912 -XX:MaxHeapSize=536870912 -XX:+PrintGC -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseSerialGC 
2020-10-25T21:05:42.171-0800: 0.209: [GC (Allocation Failure) 2020-10-25T21:05:42.171-0800: 0.209: [DefNew: 139776K->17471K(157248K), 0.0250783 secs] 139776K->42837K(506816K), 0.0252103 secs] [Times: user=0.01 sys=0.01, real=0.02 secs] 
2020-10-25T21:05:42.223-0800: 0.261: [GC (Allocation Failure) 2020-10-25T21:05:42.223-0800: 0.261: [DefNew: 157247K->17471K(157248K), 0.0585231 secs] 182613K->88560K(506816K), 0.0586263 secs] [Times: user=0.02 sys=0.01, real=0.06 secs] 
2020-10-25T21:05:42.319-0800: 0.357: [GC (Allocation Failure) 2020-10-25T21:05:42.319-0800: 0.357: [DefNew: 157214K->17470K(157248K), 0.0276917 secs] 228304K->130185K(506816K), 0.0277978 secs] [Times: user=0.02 sys=0.02, real=0.03 secs] 
2020-10-25T21:05:42.370-0800: 0.409: [GC (Allocation Failure) 2020-10-25T21:05:42.370-0800: 0.409: [DefNew: 157246K->17471K(157248K), 0.0356887 secs] 269961K->177194K(506816K), 0.0357805 secs] [Times: user=0.02 sys=0.01, real=0.03 secs] 
2020-10-25T21:05:42.431-0800: 0.469: [GC (Allocation Failure) 2020-10-25T21:05:42.431-0800: 0.469: [DefNew: 157211K->17471K(157248K), 0.0314758 secs] 316934K->224809K(506816K), 0.0315742 secs] [Times: user=0.02 sys=0.01, real=0.03 secs] 
2020-10-25T21:05:42.487-0800: 0.526: [GC (Allocation Failure) 2020-10-25T21:05:42.488-0800: 0.526: [DefNew: 157247K->17469K(157248K), 0.0278294 secs] 364585K->267874K(506816K), 0.0279223 secs] [Times: user=0.02 sys=0.01, real=0.03 secs] 
2020-10-25T21:05:42.537-0800: 0.575: [GC (Allocation Failure) 2020-10-25T21:05:42.537-0800: 0.575: [DefNew: 157218K->17471K(157248K), 0.0295826 secs] 407622K->311270K(506816K), 0.0296750 secs] [Times: user=0.02 sys=0.02, real=0.03 secs] 
2020-10-25T21:05:42.589-0800: 0.627: [GC (Allocation Failure) 2020-10-25T21:05:42.589-0800: 0.627: [DefNew: 157247K->17470K(157248K), 0.0284197 secs] 451046K->355879K(506816K), 0.0285149 secs] [Times: user=0.01 sys=0.01, real=0.03 secs] 
2020-10-25T21:05:42.639-0800: 0.677: [GC (Allocation Failure) 2020-10-25T21:05:42.639-0800: 0.677: [DefNew: 157235K->157235K(157248K), 0.0000213 secs]2020-10-25T21:05:42.639-0800: 0.677: [Tenured: 338409K->280858K(349568K), 0.0766142 secs] 495644K->280858K(506816K), [Metaspace: 2853K->2853K(1056768K)], 0.0767827 secs] [Times: user=0.07 sys=0.00, real=0.08 secs] 
2020-10-25T21:05:42.739-0800: 0.777: [GC (Allocation Failure) 2020-10-25T21:05:42.739-0800: 0.777: [DefNew: 139776K->17471K(157248K), 0.0092424 secs] 420634K->324399K(506816K), 0.0093636 secs] [Times: user=0.01 sys=0.00, real=0.01 secs] 
2020-10-25T21:05:42.797-0800: 0.835: [GC (Allocation Failure) 2020-10-25T21:05:42.797-0800: 0.835: [DefNew: 157247K->157247K(157248K), 0.0000292 secs]2020-10-25T21:05:42.797-0800: 0.835: [Tenured: 306927K->300796K(349568K), 0.0544429 secs] 464175K->300796K(506816K), [Metaspace: 2854K->2854K(1056768K)], 0.0546141 secs] [Times: user=0.05 sys=0.00, real=0.06 secs] 
2020-10-25T21:05:42.875-0800: 0.913: [GC (Allocation Failure) 2020-10-25T21:05:42.875-0800: 0.913: [DefNew: 139669K->17471K(157248K), 0.0089571 secs] 440465K->345511K(506816K), 0.0090551 secs] [Times: user=0.01 sys=0.00, real=0.01 secs] 
2020-10-25T21:05:42.911-0800: 0.949: [GC (Allocation Failure) 2020-10-25T21:05:42.911-0800: 0.949: [DefNew: 157247K->157247K(157248K), 0.0000258 secs]2020-10-25T21:05:42.911-0800: 0.949: [Tenured: 328039K->316128K(349568K), 0.0756181 secs] 485287K->316128K(506816K), [Metaspace: 2854K->2854K(1056768K)], 0.0757806 secs] [Times: user=0.07 sys=0.00, real=0.07 secs] 
2020-10-25T21:05:43.011-0800: 1.049: [GC (Allocation Failure) 2020-10-25T21:05:43.011-0800: 1.050: [DefNew: 139437K->139437K(157248K), 0.0000260 secs]2020-10-25T21:05:43.011-0800: 1.050: [Tenured: 316128K->310052K(349568K), 0.0582404 secs] 455566K->310052K(506816K), [Metaspace: 2854K->2854K(1056768K)], 0.0583944 secs] [Times: user=0.06 sys=0.00, real=0.06 secs] 
Heap
 def new generation   total 157248K, used 6290K [0x00000007a0000000, 0x00000007aaaa0000, 0x00000007aaaa0000)
  eden space 139776K,   4% used [0x00000007a0000000, 0x00000007a06248b0, 0x00000007a8880000)
  from space 17472K,   0% used [0x00000007a8880000, 0x00000007a8880000, 0x00000007a9990000)
  to   space 17472K,   0% used [0x00000007a9990000, 0x00000007a9990000, 0x00000007aaaa0000)
 tenured generation   total 349568K, used 310052K [0x00000007aaaa0000, 0x00000007c0000000, 0x00000007c0000000)
   the space 349568K,  88% used [0x00000007aaaa0000, 0x00000007bd969180, 0x00000007bd969200, 0x00000007c0000000)
 Metaspace       used 2860K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 299K, capacity 386K, committed 512K, reserved 1048576K
```

1. 共发生14次GC, 其中Minor GC 10次，Full GC 4次.
2. Minor GC 总共耗时280ms，平均28ms。
3. Full GC 总共耗时270ms, 平均67.5ms。

### Parallel GC

```shell
$ java -XX:+UseParallelGC -Xms512m -Xmx512m -Xloggc:gc.demo.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
正在执行...
执行结束!共生成对象次数:6747
```

```text
# File: gc.demo.log
OpenJDK 64-Bit Server VM (25.242-b08) for bsd-amd64 JRE (1.8.0_242-b08), built on Jan 19 2020 06:43:11 by "jenkins" with gcc 4.2.1 (Based on Apple Inc. build 5658) (LLVM build 2336.11.00)
Memory: 4k page, physical 16777216k(3511536k free)

/proc/meminfo:

CommandLine flags: -XX:InitialHeapSize=536870912 -XX:MaxHeapSize=536870912 -XX:+PrintGC -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseParallelGC 
2020-10-25T21:12:43.576-0800: 0.186: [GC (Allocation Failure) [PSYoungGen: 131584K->21503K(153088K)] 131584K->44918K(502784K), 0.0284641 secs] [Times: user=0.02 sys=0.03, real=0.03 secs] 
2020-10-25T21:12:43.630-0800: 0.240: [GC (Allocation Failure) [PSYoungGen: 153087K->21492K(153088K)] 176502K->83287K(502784K), 0.0240669 secs] [Times: user=0.02 sys=0.06, real=0.02 secs] 
2020-10-25T21:12:43.688-0800: 0.298: [GC (Allocation Failure) [PSYoungGen: 153076K->21490K(153088K)] 214871K->126397K(502784K), 0.0219286 secs] [Times: user=0.03 sys=0.05, real=0.03 secs] 
2020-10-25T21:12:43.751-0800: 0.361: [GC (Allocation Failure) [PSYoungGen: 153074K->21498K(153088K)] 257981K->166564K(502784K), 0.0206254 secs] [Times: user=0.03 sys=0.04, real=0.02 secs] 
2020-10-25T21:12:43.793-0800: 0.403: [GC (Allocation Failure) [PSYoungGen: 153082K->21487K(153088K)] 298148K->208477K(502784K), 0.0218103 secs] [Times: user=0.03 sys=0.03, real=0.02 secs] 
2020-10-25T21:12:43.836-0800: 0.446: [GC (Allocation Failure) [PSYoungGen: 153071K->21493K(80384K)] 340061K->254274K(430080K), 0.0248779 secs] [Times: user=0.03 sys=0.04, real=0.03 secs] 
2020-10-25T21:12:43.875-0800: 0.485: [GC (Allocation Failure) [PSYoungGen: 80373K->34383K(116736K)] 313154K->272477K(466432K), 0.0094848 secs] [Times: user=0.02 sys=0.01, real=0.01 secs] 
2020-10-25T21:12:43.897-0800: 0.507: [GC (Allocation Failure) [PSYoungGen: 92921K->47611K(116736K)] 331016K->292836K(466432K), 0.0102399 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
2020-10-25T21:12:43.921-0800: 0.531: [GC (Allocation Failure) [PSYoungGen: 106491K->56877K(116736K)] 351716K->308372K(466432K), 0.0164666 secs] [Times: user=0.03 sys=0.01, real=0.01 secs] 
2020-10-25T21:12:43.950-0800: 0.560: [GC (Allocation Failure) [PSYoungGen: 115730K->38719K(116736K)] 367226K->327187K(466432K), 0.0214038 secs] [Times: user=0.03 sys=0.03, real=0.02 secs] 
2020-10-25T21:12:43.971-0800: 0.581: [Full GC (Ergonomics) [PSYoungGen: 38719K->0K(116736K)] [ParOldGen: 288468K->232198K(349696K)] 327187K->232198K(466432K), [Metaspace: 2854K->2854K(1056768K)], 0.0512752 secs] [Times: user=0.11 sys=0.01, real=0.05 secs] 
2020-10-25T21:12:44.033-0800: 0.643: [GC (Allocation Failure) [PSYoungGen: 58880K->22819K(116736K)] 291078K->255017K(466432K), 0.0042003 secs] [Times: user=0.01 sys=0.00, real=0.00 secs] 
2020-10-25T21:12:44.049-0800: 0.659: [GC (Allocation Failure) [PSYoungGen: 81537K->24038K(116736K)] 313735K->277163K(466432K), 0.0075605 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
2020-10-25T21:12:44.067-0800: 0.677: [GC (Allocation Failure) [PSYoungGen: 82704K->19323K(116736K)] 335828K->294193K(466432K), 0.0078142 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
2020-10-25T21:12:44.090-0800: 0.700: [GC (Allocation Failure) [PSYoungGen: 77675K->21836K(116736K)] 352545K->315068K(466432K), 0.0139258 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
2020-10-25T21:12:44.120-0800: 0.730: [GC (Allocation Failure) [PSYoungGen: 80678K->25429K(116736K)] 373910K->339865K(466432K), 0.0168499 secs] [Times: user=0.02 sys=0.02, real=0.01 secs] 
2020-10-25T21:12:44.137-0800: 0.747: [Full GC (Ergonomics) [PSYoungGen: 25429K->0K(116736K)] [ParOldGen: 314436K->277828K(349696K)] 339865K->277828K(466432K), [Metaspace: 2854K->2854K(1056768K)], 0.0537012 secs] [Times: user=0.12 sys=0.00, real=0.06 secs] 
2020-10-25T21:12:44.206-0800: 0.816: [GC (Allocation Failure) [PSYoungGen: 58636K->17214K(116736K)] 336465K->295043K(466432K), 0.0040869 secs] [Times: user=0.01 sys=0.00, real=0.01 secs] 
2020-10-25T21:12:44.223-0800: 0.833: [GC (Allocation Failure) [PSYoungGen: 75695K->17652K(116736K)] 353524K->310834K(466432K), 0.0065384 secs] [Times: user=0.02 sys=0.00, real=0.00 secs] 
2020-10-25T21:12:44.239-0800: 0.849: [GC (Allocation Failure) [PSYoungGen: 76532K->22188K(116736K)] 369714K->332282K(466432K), 0.0080848 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
2020-10-25T21:12:44.248-0800: 0.858: [Full GC (Ergonomics) [PSYoungGen: 22188K->0K(116736K)] [ParOldGen: 310093K->285851K(349696K)] 332282K->285851K(466432K), [Metaspace: 2854K->2854K(1056768K)], 0.0501652 secs] [Times: user=0.14 sys=0.00, real=0.05 secs] 
2020-10-25T21:12:44.308-0800: 0.918: [GC (Allocation Failure) [PSYoungGen: 58880K->18758K(116736K)] 344731K->304610K(466432K), 0.0038286 secs] [Times: user=0.01 sys=0.00, real=0.01 secs] 
2020-10-25T21:12:44.323-0800: 0.933: [GC (Allocation Failure) [PSYoungGen: 77625K->19920K(116736K)] 363477K->322024K(466432K), 0.0120378 secs] [Times: user=0.01 sys=0.00, real=0.01 secs] 
2020-10-25T21:12:44.348-0800: 0.958: [GC (Allocation Failure) [PSYoungGen: 78596K->18871K(116736K)] 380700K->340497K(466432K), 0.0078255 secs] [Times: user=0.02 sys=0.01, real=0.01 secs] 
2020-10-25T21:12:44.356-0800: 0.966: [Full GC (Ergonomics) [PSYoungGen: 18871K->0K(116736K)] [ParOldGen: 321625K->300113K(349696K)] 340497K->300113K(466432K), [Metaspace: 2854K->2854K(1056768K)], 0.0466426 secs] [Times: user=0.13 sys=0.00, real=0.05 secs] 
2020-10-25T21:12:44.413-0800: 1.023: [GC (Allocation Failure) [PSYoungGen: 58880K->24180K(116736K)] 358993K->324294K(466432K), 0.0057727 secs] [Times: user=0.01 sys=0.00, real=0.00 secs] 
2020-10-25T21:12:44.428-0800: 1.038: [GC (Allocation Failure) [PSYoungGen: 83060K->22167K(116736K)] 383174K->343712K(466432K), 0.0078804 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
2020-10-25T21:12:44.436-0800: 1.046: [Full GC (Ergonomics) [PSYoungGen: 22167K->0K(116736K)] [ParOldGen: 321544K->314641K(349696K)] 343712K->314641K(466432K), [Metaspace: 2854K->2854K(1056768K)], 0.0543222 secs] [Times: user=0.14 sys=0.00, real=0.06 secs] 
Heap
 PSYoungGen      total 116736K, used 2383K [0x00000007b5580000, 0x00000007c0000000, 0x00000007c0000000)
  eden space 58880K, 4% used [0x00000007b5580000,0x00000007b57d3e00,0x00000007b8f00000)
  from space 57856K, 0% used [0x00000007b8f00000,0x00000007b8f00000,0x00000007bc780000)
  to   space 57856K, 0% used [0x00000007bc780000,0x00000007bc780000,0x00000007c0000000)
 ParOldGen       total 349696K, used 314641K [0x00000007a0000000, 0x00000007b5580000, 0x00000007b5580000)
  object space 349696K, 89% used [0x00000007a0000000,0x00000007b33446b0,0x00000007b5580000)
 Metaspace       used 2860K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 299K, capacity 386K, committed 512K, reserved 1048576K
```

1. 共发生28次GC, 其中Minor GC 23次，Full GC 5次.
2. Minor GC 总共耗时300ms，平均13ms。
3. Full GC 总共耗时270ms, 平均54ms。

### CMS GC

```shell
$ java -XX:+UseConcMarkSweepGC -Xms512m -Xmx512m -Xloggc:gc.demo.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
正在执行...
执行结束!共生成对象次数:8333
```

```text
# File: gc.demo.log
OpenJDK 64-Bit Server VM (25.242-b08) for bsd-amd64 JRE (1.8.0_242-b08), built on Jan 19 2020 06:43:11 by "jenkins" with gcc 4.2.1 (Based on Apple Inc. build 5658) (LLVM build 2336.11.00)
Memory: 4k page, physical 16777216k(2022692k free)

/proc/meminfo:

CommandLine flags: -XX:InitialHeapSize=536870912 -XX:MaxHeapSize=536870912 -XX:MaxNewSize=178958336 -XX:MaxTenuringThreshold=6 -XX:NewSize=178958336 -XX:OldPLABSize=16 -XX:OldSize=357912576 -XX:+PrintGC -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseConcMarkSweepGC -XX:+UseParNewGC 
2020-10-25T22:12:45.269-0800: 0.189: [GC (Allocation Failure) 2020-10-25T22:12:45.269-0800: 0.189: [ParNew: 139776K->17470K(157248K), 0.0219562 secs] 139776K->53019K(506816K), 0.0221282 secs] [Times: user=0.02 sys=0.05, real=0.03 secs] 
2020-10-25T22:12:45.317-0800: 0.237: [GC (Allocation Failure) 2020-10-25T22:12:45.317-0800: 0.237: [ParNew: 156666K->17468K(157248K), 0.0243765 secs] 192215K->100144K(506816K), 0.0244768 secs] [Times: user=0.03 sys=0.06, real=0.03 secs] 
2020-10-25T22:12:45.365-0800: 0.285: [GC (Allocation Failure) 2020-10-25T22:12:45.365-0800: 0.285: [ParNew: 157244K->17472K(157248K), 0.0303464 secs] 239920K->144993K(506816K), 0.0306001 secs] [Times: user=0.10 sys=0.01, real=0.03 secs] 
2020-10-25T22:12:45.419-0800: 0.339: [GC (Allocation Failure) 2020-10-25T22:12:45.419-0800: 0.339: [ParNew: 157248K->17470K(157248K), 0.0317842 secs] 284769K->192982K(506816K), 0.0318844 secs] [Times: user=0.10 sys=0.02, real=0.04 secs] 
2020-10-25T22:12:45.451-0800: 0.371: [GC (CMS Initial Mark) [1 CMS-initial-mark: 175511K(349568K)] 193270K(506816K), 0.0003234 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-10-25T22:12:45.451-0800: 0.371: [CMS-concurrent-mark-start]
2020-10-25T22:12:45.457-0800: 0.376: [CMS-concurrent-mark: 0.005/0.005 secs] [Times: user=0.01 sys=0.01, real=0.00 secs] 
2020-10-25T22:12:45.457-0800: 0.377: [CMS-concurrent-preclean-start]
2020-10-25T22:12:45.457-0800: 0.377: [CMS-concurrent-preclean: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-10-25T22:12:45.457-0800: 0.377: [CMS-concurrent-abortable-preclean-start]
2020-10-25T22:12:45.474-0800: 0.394: [GC (Allocation Failure) 2020-10-25T22:12:45.474-0800: 0.394: [ParNew: 157246K->17471K(157248K), 0.0291757 secs] 332758K->235311K(506816K), 0.0292770 secs] [Times: user=0.09 sys=0.01, real=0.03 secs] 
2020-10-25T22:12:45.526-0800: 0.446: [GC (Allocation Failure) 2020-10-25T22:12:45.526-0800: 0.446: [ParNew: 157247K->17470K(157248K), 0.0276862 secs] 375087K->275023K(506816K), 0.0277819 secs] [Times: user=0.09 sys=0.02, real=0.03 secs] 
2020-10-25T22:12:45.575-0800: 0.495: [GC (Allocation Failure) 2020-10-25T22:12:45.575-0800: 0.495: [ParNew: 157246K->17469K(157248K), 0.0338672 secs] 414799K->323961K(506816K), 0.0339640 secs] [Times: user=0.11 sys=0.02, real=0.03 secs] 
2020-10-25T22:12:45.630-0800: 0.550: [GC (Allocation Failure) 2020-10-25T22:12:45.631-0800: 0.550: [ParNew: 157245K->157245K(157248K), 0.0000226 secs]2020-10-25T22:12:45.631-0800: 0.550: [CMS2020-10-25T22:12:45.631-0800: 0.550: [CMS-concurrent-abortable-preclean: 0.005/0.173 secs] [Times: user=0.37 sys=0.05, real=0.18 secs] 
 (concurrent mode failure): 306491K->251542K(349568K), 0.0486199 secs] 463737K->251542K(506816K), [Metaspace: 2854K->2854K(1056768K)], 0.0487648 secs] [Times: user=0.05 sys=0.00, real=0.04 secs] 
2020-10-25T22:12:45.700-0800: 0.620: [GC (Allocation Failure) 2020-10-25T22:12:45.700-0800: 0.620: [ParNew: 139776K->17471K(157248K), 0.0084862 secs] 391318K->295317K(506816K), 0.0085828 secs] [Times: user=0.02 sys=0.00, real=0.00 secs] 
2020-10-25T22:12:45.709-0800: 0.628: [GC (CMS Initial Mark) [1 CMS-initial-mark: 277846K(349568K)] 295605K(506816K), 0.0001691 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-10-25T22:12:45.709-0800: 0.629: [CMS-concurrent-mark-start]
2020-10-25T22:12:45.711-0800: 0.631: [CMS-concurrent-mark: 0.002/0.002 secs] [Times: user=0.01 sys=0.00, real=0.01 secs] 
2020-10-25T22:12:45.711-0800: 0.631: [CMS-concurrent-preclean-start]
2020-10-25T22:12:45.712-0800: 0.632: [CMS-concurrent-preclean: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-10-25T22:12:45.712-0800: 0.632: [CMS-concurrent-abortable-preclean-start]
2020-10-25T22:12:45.730-0800: 0.650: [GC (Allocation Failure) 2020-10-25T22:12:45.730-0800: 0.650: [ParNew: 157247K->17471K(157248K), 0.0224134 secs] 435093K->336813K(506816K), 0.0225503 secs] [Times: user=0.06 sys=0.01, real=0.02 secs] 
2020-10-25T22:12:45.777-0800: 0.697: [GC (Allocation Failure) 2020-10-25T22:12:45.777-0800: 0.697: [ParNew: 157247K->157247K(157248K), 0.0002019 secs]2020-10-25T22:12:45.777-0800: 0.697: [CMS2020-10-25T22:12:45.777-0800: 0.697: [CMS-concurrent-abortable-preclean: 0.002/0.065 secs] [Times: user=0.11 sys=0.01, real=0.06 secs] 
 (concurrent mode failure): 319341K->293686K(349568K), 0.0652018 secs] 476589K->293686K(506816K), [Metaspace: 2854K->2854K(1056768K)], 0.0655631 secs] [Times: user=0.06 sys=0.00, real=0.07 secs] 
2020-10-25T22:12:45.867-0800: 0.787: [GC (Allocation Failure) 2020-10-25T22:12:45.867-0800: 0.787: [ParNew: 139776K->17470K(157248K), 0.0107762 secs] 433462K->339180K(506816K), 0.0108770 secs] [Times: user=0.03 sys=0.00, real=0.01 secs] 
2020-10-25T22:12:45.878-0800: 0.798: [GC (CMS Initial Mark) [1 CMS-initial-mark: 321710K(349568K)] 341976K(506816K), 0.0001392 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-10-25T22:12:45.879-0800: 0.798: [CMS-concurrent-mark-start]
2020-10-25T22:12:45.881-0800: 0.801: [CMS-concurrent-mark: 0.003/0.003 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
2020-10-25T22:12:45.881-0800: 0.801: [CMS-concurrent-preclean-start]
2020-10-25T22:12:45.882-0800: 0.802: [CMS-concurrent-preclean: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-10-25T22:12:45.883-0800: 0.802: [CMS-concurrent-abortable-preclean-start]
2020-10-25T22:12:45.883-0800: 0.802: [CMS-concurrent-abortable-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-10-25T22:12:45.883-0800: 0.803: [GC (CMS Final Remark) [YG occupancy: 43942 K (157248 K)]2020-10-25T22:12:45.883-0800: 0.803: [Rescan (parallel) , 0.0004333 secs]2020-10-25T22:12:45.883-0800: 0.803: [weak refs processing, 0.0000177 secs]2020-10-25T22:12:45.883-0800: 0.803: [class unloading, 0.0002179 secs]2020-10-25T22:12:45.883-0800: 0.803: [scrub symbol table, 0.0004479 secs]2020-10-25T22:12:45.884-0800: 0.804: [scrub string table, 0.0001596 secs][1 CMS-remark: 321710K(349568K)] 365652K(506816K), 0.0013738 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-10-25T22:12:45.884-0800: 0.804: [CMS-concurrent-sweep-start]
2020-10-25T22:12:45.885-0800: 0.805: [CMS-concurrent-sweep: 0.001/0.001 secs] [Times: user=0.01 sys=0.00, real=0.00 secs] 
2020-10-25T22:12:45.885-0800: 0.805: [CMS-concurrent-reset-start]
2020-10-25T22:12:45.885-0800: 0.805: [CMS-concurrent-reset: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-10-25T22:12:45.902-0800: 0.822: [GC (Allocation Failure) 2020-10-25T22:12:45.902-0800: 0.822: [ParNew: 157246K->17470K(157248K), 0.0203846 secs] 444651K->353299K(506816K), 0.0205533 secs] [Times: user=0.07 sys=0.00, real=0.02 secs] 
2020-10-25T22:12:45.923-0800: 0.843: [GC (CMS Initial Mark) [1 CMS-initial-mark: 335829K(349568K)] 353640K(506816K), 0.0001502 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-10-25T22:12:45.923-0800: 0.843: [CMS-concurrent-mark-start]
2020-10-25T22:12:45.926-0800: 0.846: [CMS-concurrent-mark: 0.003/0.003 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-10-25T22:12:45.926-0800: 0.846: [CMS-concurrent-preclean-start]
2020-10-25T22:12:45.926-0800: 0.846: [CMS-concurrent-preclean: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-10-25T22:12:45.926-0800: 0.846: [CMS-concurrent-abortable-preclean-start]
2020-10-25T22:12:45.926-0800: 0.846: [CMS-concurrent-abortable-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-10-25T22:12:45.927-0800: 0.846: [GC (CMS Final Remark) [YG occupancy: 37250 K (157248 K)]2020-10-25T22:12:45.927-0800: 0.846: [Rescan (parallel) , 0.0003791 secs]2020-10-25T22:12:45.927-0800: 0.847: [weak refs processing, 0.0000147 secs]2020-10-25T22:12:45.927-0800: 0.847: [class unloading, 0.0002157 secs]2020-10-25T22:12:45.927-0800: 0.847: [scrub symbol table, 0.0005418 secs]2020-10-25T22:12:45.928-0800: 0.848: [scrub string table, 0.0001610 secs][1 CMS-remark: 335829K(349568K)] 373080K(506816K), 0.0014055 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-10-25T22:12:45.928-0800: 0.848: [CMS-concurrent-sweep-start]
2020-10-25T22:12:45.929-0800: 0.849: [CMS-concurrent-sweep: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-10-25T22:12:45.929-0800: 0.849: [CMS-concurrent-reset-start]
2020-10-25T22:12:45.930-0800: 0.850: [CMS-concurrent-reset: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
2020-10-25T22:12:45.948-0800: 0.868: [GC (Allocation Failure) 2020-10-25T22:12:45.948-0800: 0.868: [ParNew: 157246K->157246K(157248K), 0.0000189 secs]2020-10-25T22:12:45.948-0800: 0.868: [CMS: 297897K->328922K(349568K), 0.0568484 secs] 455143K->328922K(506816K), [Metaspace: 2854K->2854K(1056768K)], 0.0569877 secs] [Times: user=0.06 sys=0.00, real=0.06 secs] 
2020-10-25T22:12:46.005-0800: 0.925: [GC (CMS Initial Mark) [1 CMS-initial-mark: 328922K(349568K)] 329188K(506816K), 0.0002893 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-10-25T22:12:46.006-0800: 0.925: [CMS-concurrent-mark-start]
2020-10-25T22:12:46.008-0800: 0.928: [CMS-concurrent-mark: 0.003/0.003 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-10-25T22:12:46.008-0800: 0.928: [CMS-concurrent-preclean-start]
2020-10-25T22:12:46.009-0800: 0.929: [CMS-concurrent-preclean: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-10-25T22:12:46.009-0800: 0.929: [CMS-concurrent-abortable-preclean-start]
2020-10-25T22:12:46.027-0800: 0.947: [GC (Allocation Failure) 2020-10-25T22:12:46.027-0800: 0.947: [ParNew: 139776K->139776K(157248K), 0.0000223 secs]2020-10-25T22:12:46.028-0800: 0.947: [CMS2020-10-25T22:12:46.028-0800: 0.947: [CMS-concurrent-abortable-preclean: 0.001/0.019 secs] [Times: user=0.02 sys=0.00, real=0.02 secs] 
 (concurrent mode failure): 328922K->334587K(349568K), 0.0563002 secs] 468698K->334587K(506816K), [Metaspace: 2854K->2854K(1056768K)], 0.0564579 secs] [Times: user=0.06 sys=0.00, real=0.06 secs] 
2020-10-25T22:12:46.104-0800: 1.024: [GC (Allocation Failure) 2020-10-25T22:12:46.104-0800: 1.024: [ParNew: 139120K->139120K(157248K), 0.0000243 secs]2020-10-25T22:12:46.104-0800: 1.024: [CMS: 334587K->342619K(349568K), 0.0606279 secs] 473707K->342619K(506816K), [Metaspace: 2854K->2854K(1056768K)], 0.0607943 secs] [Times: user=0.05 sys=0.01, real=0.06 secs] 
2020-10-25T22:12:46.165-0800: 1.085: [GC (CMS Initial Mark) [1 CMS-initial-mark: 342619K(349568K)] 343340K(506816K), 0.0001736 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-10-25T22:12:46.165-0800: 1.085: [CMS-concurrent-mark-start]
Heap
 par new generation   total 157248K, used 13492K [0x00000007a0000000, 0x00000007aaaa0000, 0x00000007aaaa0000)
  eden space 139776K,   9% used [0x00000007a0000000, 0x00000007a0d2d370, 0x00000007a8880000)
  from space 17472K,   0% used [0x00000007a9990000, 0x00000007a9990000, 0x00000007aaaa0000)
  to   space 17472K,   0% used [0x00000007a8880000, 0x00000007a8880000, 0x00000007a9990000)
 concurrent mark-sweep generation total 349568K, used 342619K [0x00000007aaaa0000, 0x00000007c0000000, 0x00000007c0000000)
 Metaspace       used 2860K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 299K, capacity 386K, committed 512K, reserved 1048576K
```

1. 共发生18次GC, 其中Minor GC 13次，Full GC 5次.
2. Minor GC 总共耗时390ms，平均30ms。
3. Full GC 总共耗时170ms, 平均56.7ms。

### G1 GC

```shell
$ java -XX:+UseG1GC -Xms512m -Xmx512m -Xloggc:gc.demo.log -XX:+PrintGC -XX:+PrintGCDateStamps GCLogAnalysis
正在执行...
执行结束!共生成对象次数:8862
```

```text
# File: gc.demo.log
OpenJDK 64-Bit Server VM (25.242-b08) for bsd-amd64 JRE (1.8.0_242-b08), built on Jan 19 2020 06:43:11 by "jenkins" with gcc 4.2.1 (Based on Apple Inc. build 5658) (LLVM build 2336.11.00)
Memory: 4k page, physical 16777216k(3226516k free)

/proc/meminfo:

CommandLine flags: -XX:InitialHeapSize=536870912 -XX:MaxHeapSize=536870912 -XX:+PrintGC -XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseG1GC 
2020-10-27T22:07:47.534-0800: 0.137: [GC pause (G1 Evacuation Pause) (young) 35329K->14682K(512M), 0.0048315 secs]
2020-10-27T22:07:47.552-0800: 0.154: [GC pause (G1 Evacuation Pause) (young) 38787K->23399K(512M), 0.0062392 secs]
2020-10-27T22:07:47.567-0800: 0.170: [GC pause (G1 Evacuation Pause) (young) 49541K->32077K(512M), 0.0025617 secs]
2020-10-27T22:07:47.594-0800: 0.196: [GC pause (G1 Evacuation Pause) (young) 90335K->49537K(512M), 0.0074355 secs]
2020-10-27T22:07:47.615-0800: 0.217: [GC pause (G1 Evacuation Pause) (young) 102M->66567K(512M), 0.0068672 secs]
2020-10-27T22:07:47.652-0800: 0.255: [GC pause (G1 Evacuation Pause) (young) 159M->99120K(512M), 0.0097386 secs]
2020-10-27T22:07:47.686-0800: 0.288: [GC pause (G1 Evacuation Pause) (young) 197M->130M(512M), 0.0113444 secs]
2020-10-27T22:07:47.724-0800: 0.326: [GC pause (G1 Evacuation Pause) (young) 240M->165M(512M), 0.0117222 secs]
2020-10-27T22:07:47.785-0800: 0.387: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 326M->214M(512M), 0.0161693 secs]
2020-10-27T22:07:47.801-0800: 0.403: [GC concurrent-root-region-scan-start]
2020-10-27T22:07:47.801-0800: 0.404: [GC concurrent-root-region-scan-end, 0.0003069 secs]
2020-10-27T22:07:47.801-0800: 0.404: [GC concurrent-mark-start]
2020-10-27T22:07:47.804-0800: 0.406: [GC concurrent-mark-end, 0.0025383 secs]
2020-10-27T22:07:47.804-0800: 0.406: [GC remark, 0.0013608 secs]
2020-10-27T22:07:47.806-0800: 0.408: [GC cleanup 228M->228M(512M), 0.0005147 secs]
2020-10-27T22:07:47.861-0800: 0.464: [GC pause (G1 Evacuation Pause) (young)-- 424M->357M(512M), 0.0105929 secs]
2020-10-27T22:07:47.873-0800: 0.476: [GC pause (G1 Evacuation Pause) (mixed) 360M->342M(512M), 0.0044847 secs]
2020-10-27T22:07:47.878-0800: 0.480: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 343M->342M(512M), 0.0018404 secs]
2020-10-27T22:07:47.880-0800: 0.482: [GC concurrent-root-region-scan-start]
2020-10-27T22:07:47.880-0800: 0.482: [GC concurrent-root-region-scan-end, 0.0001690 secs]
2020-10-27T22:07:47.880-0800: 0.482: [GC concurrent-mark-start]
2020-10-27T22:07:47.882-0800: 0.484: [GC concurrent-mark-end, 0.0017629 secs]
2020-10-27T22:07:47.882-0800: 0.484: [GC remark, 0.0016279 secs]
2020-10-27T22:07:47.884-0800: 0.486: [GC cleanup 353M->353M(512M), 0.0004842 secs]
2020-10-27T22:07:47.900-0800: 0.503: [GC pause (G1 Evacuation Pause) (young) 431M->367M(512M), 0.0039067 secs]
2020-10-27T22:07:47.909-0800: 0.511: [GC pause (G1 Evacuation Pause) (mixed) 389M->325M(512M), 0.0034556 secs]
2020-10-27T22:07:47.917-0800: 0.519: [GC pause (G1 Evacuation Pause) (mixed) 352M->299M(512M), 0.0050615 secs]
2020-10-27T22:07:47.926-0800: 0.529: [GC pause (G1 Evacuation Pause) (mixed) 325M->287M(512M), 0.0052965 secs]
2020-10-27T22:07:47.932-0800: 0.535: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 289M->287M(512M), 0.0011361 secs]
2020-10-27T22:07:47.933-0800: 0.536: [GC concurrent-root-region-scan-start]
2020-10-27T22:07:47.934-0800: 0.536: [GC concurrent-root-region-scan-end, 0.0001951 secs]
2020-10-27T22:07:47.934-0800: 0.536: [GC concurrent-mark-start]
2020-10-27T22:07:47.936-0800: 0.538: [GC concurrent-mark-end, 0.0018696 secs]
2020-10-27T22:07:47.936-0800: 0.538: [GC remark, 0.0019097 secs]
2020-10-27T22:07:47.938-0800: 0.540: [GC cleanup 299M->299M(512M), 0.0004888 secs]
2020-10-27T22:07:47.959-0800: 0.561: [GC pause (G1 Evacuation Pause) (young) 410M->317M(512M), 0.0054498 secs]
2020-10-27T22:07:47.967-0800: 0.570: [GC pause (G1 Evacuation Pause) (mixed) 333M->301M(512M), 0.0067059 secs]
2020-10-27T22:07:47.975-0800: 0.577: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 305M->302M(512M), 0.0014769 secs]
2020-10-27T22:07:47.976-0800: 0.579: [GC concurrent-root-region-scan-start]
2020-10-27T22:07:47.977-0800: 0.579: [GC concurrent-root-region-scan-end, 0.0001862 secs]
2020-10-27T22:07:47.977-0800: 0.579: [GC concurrent-mark-start]
2020-10-27T22:07:47.978-0800: 0.581: [GC concurrent-mark-end, 0.0019220 secs]
2020-10-27T22:07:47.979-0800: 0.581: [GC remark, 0.0017474 secs]
2020-10-27T22:07:47.981-0800: 0.583: [GC cleanup 316M->316M(512M), 0.0004872 secs]
2020-10-27T22:07:47.998-0800: 0.600: [GC pause (G1 Evacuation Pause) (young) 405M->330M(512M), 0.0052054 secs]
2020-10-27T22:07:48.007-0800: 0.609: [GC pause (G1 Evacuation Pause) (mixed) 348M->311M(512M), 0.0065511 secs]
2020-10-27T22:07:48.014-0800: 0.616: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 313M->311M(512M), 0.0012552 secs]
2020-10-27T22:07:48.015-0800: 0.617: [GC concurrent-root-region-scan-start]
2020-10-27T22:07:48.015-0800: 0.618: [GC concurrent-root-region-scan-end, 0.0001925 secs]
2020-10-27T22:07:48.015-0800: 0.618: [GC concurrent-mark-start]
2020-10-27T22:07:48.017-0800: 0.619: [GC concurrent-mark-end, 0.0014766 secs]
2020-10-27T22:07:48.017-0800: 0.619: [GC remark, 0.0019309 secs]
2020-10-27T22:07:48.019-0800: 0.621: [GC cleanup 320M->320M(512M), 0.0004394 secs]
2020-10-27T22:07:48.036-0800: 0.638: [GC pause (G1 Evacuation Pause) (young) 409M->344M(512M), 0.0057403 secs]
2020-10-27T22:07:48.046-0800: 0.648: [GC pause (G1 Evacuation Pause) (mixed) 367M->331M(512M), 0.0054790 secs]
2020-10-27T22:07:48.052-0800: 0.654: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 332M->331M(512M), 0.0014245 secs]
2020-10-27T22:07:48.053-0800: 0.655: [GC concurrent-root-region-scan-start]
2020-10-27T22:07:48.053-0800: 0.656: [GC concurrent-root-region-scan-end, 0.0001972 secs]
2020-10-27T22:07:48.053-0800: 0.656: [GC concurrent-mark-start]
2020-10-27T22:07:48.055-0800: 0.657: [GC concurrent-mark-end, 0.0017797 secs]
2020-10-27T22:07:48.055-0800: 0.658: [GC remark, 0.0018426 secs]
2020-10-27T22:07:48.057-0800: 0.660: [GC cleanup 342M->342M(512M), 0.0005127 secs]
2020-10-27T22:07:48.069-0800: 0.671: [GC pause (G1 Evacuation Pause) (young) 403M->353M(512M), 0.0042786 secs]
2020-10-27T22:07:48.077-0800: 0.680: [GC pause (G1 Evacuation Pause) (mixed) 373M->336M(512M), 0.0067301 secs]
2020-10-27T22:07:48.086-0800: 0.688: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 337M->336M(512M), 0.0016853 secs]
2020-10-27T22:07:48.088-0800: 0.690: [GC concurrent-root-region-scan-start]
2020-10-27T22:07:48.091-0800: 0.694: [GC concurrent-root-region-scan-end, 0.0032715 secs]
2020-10-27T22:07:48.091-0800: 0.694: [GC concurrent-mark-start]
2020-10-27T22:07:48.095-0800: 0.697: [GC concurrent-mark-end, 0.0034741 secs]
2020-10-27T22:07:48.095-0800: 0.697: [GC remark, 0.0024741 secs]
2020-10-27T22:07:48.098-0800: 0.700: [GC cleanup 371M->371M(512M), 0.0007810 secs]
2020-10-27T22:07:48.112-0800: 0.714: [GC pause (G1 Evacuation Pause) (young) 401M->351M(512M), 0.0059746 secs]
2020-10-27T22:07:48.122-0800: 0.724: [GC pause (G1 Evacuation Pause) (mixed) 372M->339M(512M), 0.0057902 secs]
2020-10-27T22:07:48.129-0800: 0.731: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 341M->340M(512M), 0.0014501 secs]
2020-10-27T22:07:48.130-0800: 0.733: [GC concurrent-root-region-scan-start]
2020-10-27T22:07:48.130-0800: 0.733: [GC concurrent-root-region-scan-end, 0.0000849 secs]
2020-10-27T22:07:48.130-0800: 0.733: [GC concurrent-mark-start]
2020-10-27T22:07:48.132-0800: 0.735: [GC concurrent-mark-end, 0.0020145 secs]
2020-10-27T22:07:48.132-0800: 0.735: [GC remark, 0.0021804 secs]
2020-10-27T22:07:48.135-0800: 0.737: [GC cleanup 352M->352M(512M), 0.0004992 secs]
2020-10-27T22:07:48.144-0800: 0.746: [GC pause (G1 Evacuation Pause) (young) 393M->355M(512M), 0.0036369 secs]
2020-10-27T22:07:48.152-0800: 0.755: [GC pause (G1 Evacuation Pause) (mixed) 378M->343M(512M), 0.0064570 secs]
2020-10-27T22:07:48.160-0800: 0.762: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 349M->344M(512M), 0.0014633 secs]
2020-10-27T22:07:48.162-0800: 0.764: [GC concurrent-root-region-scan-start]
2020-10-27T22:07:48.162-0800: 0.764: [GC concurrent-root-region-scan-end, 0.0001610 secs]
2020-10-27T22:07:48.162-0800: 0.764: [GC concurrent-mark-start]
2020-10-27T22:07:48.164-0800: 0.766: [GC concurrent-mark-end, 0.0023440 secs]
2020-10-27T22:07:48.164-0800: 0.767: [GC remark, 0.0019694 secs]
2020-10-27T22:07:48.166-0800: 0.769: [GC cleanup 357M->357M(512M), 0.0004725 secs]
2020-10-27T22:07:48.175-0800: 0.778: [GC pause (G1 Evacuation Pause) (young) 401M->360M(512M), 0.0039392 secs]
2020-10-27T22:07:48.183-0800: 0.786: [GC pause (G1 Evacuation Pause) (mixed) 381M->351M(512M), 0.0075027 secs]
2020-10-27T22:07:48.191-0800: 0.794: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 352M->351M(512M), 0.0016607 secs]
2020-10-27T22:07:48.193-0800: 0.795: [GC concurrent-root-region-scan-start]
2020-10-27T22:07:48.193-0800: 0.796: [GC concurrent-root-region-scan-end, 0.0003536 secs]
2020-10-27T22:07:48.193-0800: 0.796: [GC concurrent-mark-start]
2020-10-27T22:07:48.195-0800: 0.798: [GC concurrent-mark-end, 0.0018579 secs]
2020-10-27T22:07:48.195-0800: 0.798: [GC remark, 0.0019664 secs]
2020-10-27T22:07:48.198-0800: 0.800: [GC cleanup 366M->366M(512M), 0.0007508 secs]
2020-10-27T22:07:48.208-0800: 0.810: [GC pause (G1 Evacuation Pause) (young) 400M->362M(512M), 0.0037720 secs]
2020-10-27T22:07:48.216-0800: 0.818: [GC pause (G1 Evacuation Pause) (mixed) 386M->353M(512M), 0.0080178 secs]
2020-10-27T22:07:48.224-0800: 0.827: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 355M->354M(512M), 0.0015536 secs]
2020-10-27T22:07:48.226-0800: 0.828: [GC concurrent-root-region-scan-start]
2020-10-27T22:07:48.226-0800: 0.828: [GC concurrent-root-region-scan-end, 0.0002570 secs]
2020-10-27T22:07:48.226-0800: 0.828: [GC concurrent-mark-start]
2020-10-27T22:07:48.228-0800: 0.830: [GC concurrent-mark-end, 0.0019737 secs]
2020-10-27T22:07:48.228-0800: 0.831: [GC remark, 0.0019329 secs]
2020-10-27T22:07:48.230-0800: 0.833: [GC cleanup 364M->364M(512M), 0.0005407 secs]
2020-10-27T22:07:48.240-0800: 0.842: [GC pause (G1 Evacuation Pause) (young) 404M->364M(512M), 0.0038765 secs]
2020-10-27T22:07:48.248-0800: 0.851: [GC pause (G1 Evacuation Pause) (mixed) 389M->358M(512M), 0.0077106 secs]
2020-10-27T22:07:48.257-0800: 0.859: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 359M->358M(512M), 0.0012775 secs]
2020-10-27T22:07:48.258-0800: 0.860: [GC concurrent-root-region-scan-start]
2020-10-27T22:07:48.258-0800: 0.861: [GC concurrent-root-region-scan-end, 0.0003301 secs]
2020-10-27T22:07:48.258-0800: 0.861: [GC concurrent-mark-start]
2020-10-27T22:07:48.261-0800: 0.863: [GC concurrent-mark-end, 0.0023518 secs]
2020-10-27T22:07:48.261-0800: 0.863: [GC remark, 0.0022476 secs]
2020-10-27T22:07:48.263-0800: 0.865: [GC cleanup 373M->373M(512M), 0.0005107 secs]
2020-10-27T22:07:48.269-0800: 0.872: [GC pause (G1 Evacuation Pause) (young) 404M->373M(512M), 0.0038384 secs]
2020-10-27T22:07:48.278-0800: 0.881: [GC pause (G1 Evacuation Pause) (mixed) 396M->364M(512M), 0.0061601 secs]
2020-10-27T22:07:48.285-0800: 0.887: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 367M->364M(512M), 0.0014554 secs]
2020-10-27T22:07:48.286-0800: 0.889: [GC concurrent-root-region-scan-start]
2020-10-27T22:07:48.287-0800: 0.889: [GC concurrent-root-region-scan-end, 0.0001681 secs]
2020-10-27T22:07:48.287-0800: 0.889: [GC concurrent-mark-start]
2020-10-27T22:07:48.289-0800: 0.891: [GC concurrent-mark-end, 0.0019959 secs]
2020-10-27T22:07:48.289-0800: 0.891: [GC remark, 0.0019862 secs]
2020-10-27T22:07:48.291-0800: 0.893: [GC cleanup 378M->378M(512M), 0.0005043 secs]
2020-10-27T22:07:48.297-0800: 0.899: [GC pause (G1 Evacuation Pause) (young) 403M->373M(512M), 0.0033306 secs]
2020-10-27T22:07:48.305-0800: 0.908: [GC pause (G1 Evacuation Pause) (mixed) 401M->365M(512M), 0.0071945 secs]
2020-10-27T22:07:48.313-0800: 0.915: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 367M->365M(512M), 0.0016183 secs]
2020-10-27T22:07:48.315-0800: 0.917: [GC concurrent-root-region-scan-start]
2020-10-27T22:07:48.315-0800: 0.917: [GC concurrent-root-region-scan-end, 0.0000399 secs]
2020-10-27T22:07:48.315-0800: 0.917: [GC concurrent-mark-start]
2020-10-27T22:07:48.317-0800: 0.919: [GC concurrent-mark-end, 0.0021206 secs]
2020-10-27T22:07:48.317-0800: 0.919: [GC remark, 0.0023016 secs]
2020-10-27T22:07:48.319-0800: 0.922: [GC cleanup 378M->378M(512M), 0.0007451 secs]
2020-10-27T22:07:48.326-0800: 0.928: [GC pause (G1 Evacuation Pause) (young) 404M->376M(512M), 0.0037278 secs]
2020-10-27T22:07:48.335-0800: 0.937: [GC pause (G1 Evacuation Pause) (mixed) 404M->370M(512M), 0.0078498 secs]
2020-10-27T22:07:48.343-0800: 0.946: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 373M->370M(512M), 0.0017730 secs]
2020-10-27T22:07:48.345-0800: 0.948: [GC concurrent-root-region-scan-start]
2020-10-27T22:07:48.345-0800: 0.948: [GC concurrent-root-region-scan-end, 0.0001479 secs]
2020-10-27T22:07:48.345-0800: 0.948: [GC concurrent-mark-start]
2020-10-27T22:07:48.347-0800: 0.950: [GC concurrent-mark-end, 0.0018109 secs]
2020-10-27T22:07:48.347-0800: 0.950: [GC remark, 0.0024324 secs]
2020-10-27T22:07:48.350-0800: 0.952: [GC cleanup 379M->379M(512M), 0.0005965 secs]
2020-10-27T22:07:48.355-0800: 0.958: [GC pause (G1 Evacuation Pause) (young) 401M->379M(512M), 0.0034356 secs]
2020-10-27T22:07:48.364-0800: 0.967: [GC pause (G1 Evacuation Pause) (mixed) 407M->371M(512M), 0.0074733 secs]
2020-10-27T22:07:48.373-0800: 0.975: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 374M->371M(512M), 0.0012680 secs]
2020-10-27T22:07:48.374-0800: 0.976: [GC concurrent-root-region-scan-start]
2020-10-27T22:07:48.374-0800: 0.977: [GC concurrent-root-region-scan-end, 0.0001710 secs]
2020-10-27T22:07:48.374-0800: 0.977: [GC concurrent-mark-start]
2020-10-27T22:07:48.376-0800: 0.979: [GC concurrent-mark-end, 0.0019712 secs]
2020-10-27T22:07:48.376-0800: 0.979: [GC remark, 0.0024638 secs]
2020-10-27T22:07:48.379-0800: 0.981: [GC cleanup 383M->383M(512M), 0.0004559 secs]
2020-10-27T22:07:48.383-0800: 0.986: [GC pause (G1 Evacuation Pause) (young) 403M->379M(512M), 0.0038008 secs]
2020-10-27T22:07:48.392-0800: 0.995: [GC pause (G1 Evacuation Pause) (mixed) 404M->368M(512M), 0.0065457 secs]
2020-10-27T22:07:48.399-0800: 1.002: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 370M->368M(512M), 0.0013765 secs]
2020-10-27T22:07:48.401-0800: 1.003: [GC concurrent-root-region-scan-start]
2020-10-27T22:07:48.401-0800: 1.004: [GC concurrent-root-region-scan-end, 0.0003397 secs]
2020-10-27T22:07:48.401-0800: 1.004: [GC concurrent-mark-start]
2020-10-27T22:07:48.403-0800: 1.006: [GC concurrent-mark-end, 0.0020657 secs]
2020-10-27T22:07:48.403-0800: 1.006: [GC remark, 0.0020876 secs]
2020-10-27T22:07:48.406-0800: 1.008: [GC cleanup 380M->380M(512M), 0.0004684 secs]
2020-10-27T22:07:48.411-0800: 1.014: [GC pause (G1 Evacuation Pause) (young) 405M->379M(512M), 0.0037515 secs]
2020-10-27T22:07:48.419-0800: 1.022: [GC pause (G1 Evacuation Pause) (mixed) 403M->374M(512M), 0.0054257 secs]
2020-10-27T22:07:48.425-0800: 1.027: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 374M->374M(512M), 0.0013411 secs]
2020-10-27T22:07:48.426-0800: 1.029: [GC concurrent-root-region-scan-start]
2020-10-27T22:07:48.426-0800: 1.029: [GC concurrent-root-region-scan-end, 0.0000132 secs]
2020-10-27T22:07:48.426-0800: 1.029: [GC concurrent-mark-start]
2020-10-27T22:07:48.429-0800: 1.031: [GC concurrent-mark-end, 0.0022228 secs]
2020-10-27T22:07:48.429-0800: 1.031: [GC remark, 0.0021940 secs]
2020-10-27T22:07:48.431-0800: 1.033: [GC cleanup 387M->387M(512M), 0.0005750 secs]
2020-10-27T22:07:48.435-0800: 1.038: [GC pause (G1 Evacuation Pause) (young) 406M->384M(512M), 0.0030126 secs]
2020-10-27T22:07:48.443-0800: 1.045: [GC pause (G1 Evacuation Pause) (mixed) 411M->376M(512M), 0.0064624 secs]
2020-10-27T22:07:48.450-0800: 1.052: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 377M->376M(512M), 0.0013864 secs]
2020-10-27T22:07:48.451-0800: 1.054: [GC concurrent-root-region-scan-start]
2020-10-27T22:07:48.451-0800: 1.054: [GC concurrent-root-region-scan-end, 0.0000270 secs]
2020-10-27T22:07:48.451-0800: 1.054: [GC concurrent-mark-start]
2020-10-27T22:07:48.454-0800: 1.056: [GC concurrent-mark-end, 0.0024426 secs]
2020-10-27T22:07:48.454-0800: 1.056: [GC remark, 0.0020318 secs]
2020-10-27T22:07:48.456-0800: 1.058: [GC cleanup 390M->390M(512M), 0.0005580 secs]
2020-10-27T22:07:48.461-0800: 1.063: [GC pause (G1 Evacuation Pause) (young) 408M->386M(512M), 0.0031508 secs]
2020-10-27T22:07:48.469-0800: 1.071: [GC pause (G1 Evacuation Pause) (mixed) 412M->378M(512M), 0.0064061 secs]
2020-10-27T22:07:48.476-0800: 1.078: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 381M->378M(512M), 0.0018625 secs]
2020-10-27T22:07:48.478-0800: 1.080: [GC concurrent-root-region-scan-start]
2020-10-27T22:07:48.478-0800: 1.080: [GC concurrent-root-region-scan-end, 0.0001819 secs]
2020-10-27T22:07:48.478-0800: 1.080: [GC concurrent-mark-start]
2020-10-27T22:07:48.480-0800: 1.082: [GC concurrent-mark-end, 0.0020610 secs]
2020-10-27T22:07:48.480-0800: 1.082: [GC remark, 0.0021444 secs]
2020-10-27T22:07:48.483-0800: 1.085: [GC cleanup 392M->392M(512M), 0.0006277 secs]
2020-10-27T22:07:48.487-0800: 1.089: [GC pause (G1 Evacuation Pause) (young) 409M->389M(512M), 0.0037692 secs]
```

## Q2 使用压测工具（wrk或sb），演练gateway-server-0.0.1-SNAPSHOT.jar 示例。

### Serial GC

* heap size 512M

```shell
$ java -jar -XX:+UseSerialGC -Xms512m -Xmx512m gateway-server-0.0.1-SNAPSHOT.jar

# 4 threads, 40 connections, duration 60 seconds
$ wrk -t4 -c40 -d60s --latency http://localhost:8088/api/hello
  Running 1m test @ http://localhost:8088/api/hello
    4 threads and 40 connections
    Thread Stats   Avg      Stdev     Max   +/- Stdev
      Latency     3.90ms   11.16ms 227.02ms   95.08%
      Req/Sec     5.66k     2.19k   16.12k    71.15%
    Latency Distribution
       50%    1.34ms
       75%    2.09ms
       90%    5.76ms
       99%   57.73ms
    1341740 requests in 1.00m, 160.19MB read
  Requests/sec:  22348.57
  Transfer/sec:      2.67MB
```

* heap size 1G

```shell
$ java -jar -XX:+UseSerialGC -Xms1g -Xmx1g gateway-server-0.0.1-SNAPSHOT.jar

# 4 threads, 40 connections, duration 60 seconds
$ wrk -t4 -c40 -d60s --latency http://localhost:8088/api/hello
  Running 1m test @ http://localhost:8088/api/hello
    4 threads and 40 connections
    Thread Stats   Avg      Stdev     Max   +/- Stdev
      Latency     4.97ms   14.22ms 310.36ms   94.13%
      Req/Sec     5.38k     2.13k   14.46k    71.28%
    Latency Distribution
       50%    1.41ms
       75%    2.29ms
       90%    7.74ms
       99%   74.74ms
    1273981 requests in 1.00m, 152.10MB read
  Requests/sec:  21215.42
  Transfer/sec:      2.53MB
```

### Parallel GC

* heap size 512M

```shell
$ java -jar -XX:+UseParallelGC -Xms512m -Xmx512m gateway-server-0.0.1-SNAPSHOT.jar

# 4 threads, 40 connections, duration 60 seconds
$ wrk -t4 -c40 -d60s --latency http://localhost:8088/api/hello
  Running 1m test @ http://localhost:8088/api/hello
    4 threads and 40 connections
    Thread Stats   Avg      Stdev     Max   +/- Stdev
      Latency     5.29ms   24.45ms 429.82ms   97.11%
      Req/Sec     6.29k     3.06k   16.77k    63.52%
    Latency Distribution
       50%    1.05ms
       75%    2.24ms
       90%    6.86ms
       99%   87.95ms
    1484363 requests in 1.00m, 177.22MB read
  Requests/sec:  24721.29
  Transfer/sec:      2.95MB
  
```

* heap size 1g

```shell
$ java -jar -XX:+UseParallelGC -Xms1g -Xmx1g gateway-server-0.0.1-SNAPSHOT.jar

# 4 threads, 40 connections, duration 60 seconds
$ wrk -t4 -c40 -d60s --latency http://localhost:8088/api/hello
  Running 1m test @ http://localhost:8088/api/hello
    4 threads and 40 connections
    Thread Stats   Avg      Stdev     Max   +/- Stdev
      Latency     4.77ms   16.56ms 394.14ms   95.49%
      Req/Sec     5.91k     2.79k   17.36k    66.87%
    Latency Distribution
       50%    1.18ms
       75%    2.31ms
       90%    7.45ms
       99%   72.12ms
    1400274 requests in 1.00m, 167.18MB read
  Requests/sec:  23310.44
  Transfer/sec:      2.78MB
  
```

### CMS GC

* heap size 512M

```shell
$ java -jar -XX:+UseConcMarkSweepGC -Xms512m -Xmx512m gateway-server-0.0.1-SNAPSHOT.jar

# 4 threads, 40 connections, duration 60 seconds
$ wrk -t4 -c40 -d60s --latency http://localhost:8088/api/hello
  Running 1m test @ http://localhost:8088/api/hello
    4 threads and 40 connections
    Thread Stats   Avg      Stdev     Max   +/- Stdev
      Latency     4.98ms   20.14ms 391.01ms   96.10%
      Req/Sec     6.02k     2.35k   15.11k    70.47%
    Latency Distribution
       50%    1.29ms
       75%    2.00ms
       90%    5.72ms
       99%   81.15ms
    1425559 requests in 1.00m, 170.20MB read
  Requests/sec:  23722.12
  Transfer/sec:      2.83MB

```

* heap size 1g

```shell
$ java -jar -XX:+UseConcMarkSweepGC -Xms1g -Xmx1g gateway-server-0.0.1-SNAPSHOT.jar

# 4 threads, 40 connections, duration 60 seconds
$ wrk -t4 -c40 -d60s --latency http://localhost:8088/api/hello
  Running 1m test @ http://localhost:8088/api/hello
    4 threads and 40 connections
    Thread Stats   Avg      Stdev     Max   +/- Stdev
      Latency     4.43ms   13.08ms 240.55ms   94.94%
      Req/Sec     5.30k     1.77k   14.83k    76.37%
    Latency Distribution
       50%    1.49ms
       75%    2.25ms
       90%    5.39ms
       99%   71.54ms
    1256694 requests in 1.00m, 150.04MB read
  Requests/sec:  20930.68
  Transfer/sec:      2.50MB 
```

### G1 GC

* heap size 512M

```shell
$ java -jar -XX:+UseG1GC -Xms512m -Xmx512m gateway-server-0.0.1-SNAPSHOT.jar

# 4 threads, 40 connections, duration 60 seconds
$ wrk -t4 -c40 -d60s --latency http://localhost:8088/api/hello
  Running 1m test @ http://localhost:8088/api/hello
    4 threads and 40 connections
    Thread Stats   Avg      Stdev     Max   +/- Stdev
      Latency     3.41ms    8.33ms 180.44ms   94.66%
      Req/Sec     5.42k     1.98k   15.98k    72.71%
    Latency Distribution
       50%    1.45ms
       75%    2.12ms
       90%    5.00ms
       99%   47.33ms
    1289435 requests in 1.00m, 153.95MB read
  Requests/sec:  21458.49
  Transfer/sec:      2.56MB
  
```

* heap size 1g

```shell
$ java -jar -XX:+UseParallelGC -Xms1g -Xmx1g gateway-server-0.0.1-SNAPSHOT.jar

# 4 threads, 40 connections, duration 60 seconds
$ wrk -t4 -c40 -d60s --latency http://localhost:8088/api/hello
  Running 1m test @ http://localhost:8088/api/hello
    4 threads and 40 connections
      Thread Stats   Avg      Stdev     Max   +/- Stdev
      Latency     6.11ms   36.25ms 637.45ms   97.82%
      Req/Sec     5.43k     1.84k   17.88k    75.04%
    Latency Distribution
       50%    1.47ms
       75%    2.11ms
       90%    4.38ms
       99%   81.47ms
    1282779 requests in 1.00m, 153.15MB read
  Requests/sec:  21345.04
  Transfer/sec:      2.55MB
```

* heap size 4g

```shell
$ java -jar -XX:+UseParallelGC -Xms4g -Xmx4g gateway-server-0.0.1-SNAPSHOT.jar

# 4 threads, 40 connections, duration 60 seconds
$ wrk -t4 -c40 -d60s --latency http://localhost:8088/api/hello
  Running 1m test @ http://localhost:8088/api/hello
    4 threads and 40 connections
    Thread Stats   Avg      Stdev     Max   +/- Stdev
      Latency     5.82ms   32.70ms 602.50ms   97.62%
      Req/Sec     6.21k     2.82k   16.90k    65.43%
    Latency Distribution
       50%    1.12ms
       75%    2.04ms
       90%    6.23ms
       99%   73.76ms
    1466298 requests in 1.00m, 175.06MB read
  Requests/sec:  24402.08
  Transfer/sec:      2.91MB
```

## 总结

### 1. Serial GC
  * 单线程，实现简单, 年轻代采用复制算法，老年代采用标记-整理算法。
  * 不能充分利用cpu多核，随着堆内存的变大，Full GC STW的时间会变大。
  * 适合堆内存不大的情况。 
  
### 2. Parallel GC
  * 多线程，并发执行，充分利用cpu多核，年轻代采用复制算法，老年代采用标记-整理算法。
  * 适用于注重于吞吐量及CPU资源敏感的场合
  
### 3. CMS GC
  * 多线程，并行执行，利用cpu多核，注重减少latency。一般与ParNew GC配合使用，老年代采用标记-清除算法。
  * 老年代存在内存碎片化问题，随着堆内存变大，会增加STW的时间。
  * 适用于注重latency的场合。
  
### 4. G1 GC
  * 将内存划分成多个Region，使用标记-整理算法。
  * 可以设置STW时间。
  * 适用于大内存、兼顾吞吐量和停顿时间的场合。
  
## Q4 写一段代码，使用 HttpClient 或 OkHttp 访问 http://localhost:8801，代码提交到Github。

How to run

```shell
cd httpdemo
mvn clean package
java -jar target/httpdemo-1.0-SNAPSHOT.jar
```
