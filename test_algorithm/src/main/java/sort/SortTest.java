package sort;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author zl
 * @Version 1.0
 * @Description TODO
 * @date 2018/11/12  11:37
 */
public class SortTest {

    @Test
    public void testBubble() {
        int[] arr = new int[20];
        for (int i = 0; i < arr.length; i++) {
            int values = (int)(Math.random() * 100 + 1);
            arr[i] = values;
        }
        ArrayList<Integer> arrayList = new ArrayList<>(arr.length);
        for (int i : arr) {
            arrayList.add(i);
        }
        System.out.println(Arrays.toString(arr));
        long startTime = System.nanoTime();
        int[] result =
            //bubbleSort(arr);
            //selectionSort(arr);
            //insertionSort(arr);
            //shellSort(arr);
            // MergeSort(arr);
            //QuickSort(arr, 0, arr.length - 1);
            //HeapSort(arr);
            //CountingSort(arr);
            //BucketSort(arrayList,5).toArray(new Integer[arr.length]);
            RadixSort(arr);
        System.out.println("耗时：" + (System.nanoTime() - startTime));
        System.out.println(Arrays.toString(result));
    }

    /**
     * 冒泡排序是一种简单的排序算法。它重复地走访过要排序的数列，一次比较两个元素，如果它们的顺序错误就把它们交换过来。走访数列的工作是重复地进行直到没有再需要交换，也就是说该数列已经排序完成。这个算法的名字由来是因为越小的元素会经由交换慢慢“浮”到数列的顶端。
     * <p>
     * 比较相邻的元素。如果第一个比第二个大，就交换它们两个；
     * <p>
     * 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对，这样在最后的元素应该会是最大的数；
     * <p>
     * 针对所有的元素重复以上的步骤，除了最后一个；
     * <p>
     * 重复步骤1~3，直到排序完成。
     */
    public static int[] bubbleSort(int[] arr) {
        if (null == arr || arr.length < 1) { return arr; }
        for (int i = 0; i < arr.length - 1; i++) {//循环第一层 从下标0，到倒数第二个数字
            for (int j = 0; j < arr.length - 1 - i; j++) {
                //前后元素对比交换
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
        return arr;
    }

    /**
     * 表现最稳定的排序算法之一，因为无论什么数据进去都是O(n2)的时间复杂度，所以用到它的时候，数据规模越小越好。唯一的好处可能就是不占用额外的内存空间了吧。理论上讲，选择排序可能也是平时排序一般人想到的最多的排序方法了吧。
     * <p>
     * 选择排序(Selection-sort)是一种简单直观的排序算法。它的工作原理：首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。以此类推，直到所有元素均排序完毕。
     * <p>
     * n个记录的直接选择排序可经过n-1趟直接选择排序得到有序结果。具体算法描述如下：
     * <p>
     * 初始状态：无序区为R[1..n]，有序区为空；
     * <p>
     * 第i趟排序(i=1,2,3…n-1)开始时，当前有序区和无序区分别为R[1..i-1]和R(i..n）。该趟排序从当前无序区中-选出关键字最小的记录 R[k]，将它与无序区的第1个记录R交换，使R[1..i]和R[i+1..n)分别变为记录个数增加1个的新有序区和记录个数减少1个的新无序区；
     * <p>
     * n-1趟结束，数组有序化了。
     */
    public static int[] selectionSort(int[] arr) {
        if (null == arr || arr.length < 1) { return arr; }
        int length = arr.length;
        int minIndex, tmp;
        for (int i = 0; i < length - 1; i++) {
            minIndex = i;
            for (int j = i + 1; j < length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            tmp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = tmp;
        }
        return arr;
    }

    /**
     * 插入排序（Insertion-Sort）的算法描述是一种简单直观的排序算法。它的工作原理是通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。插入排序在实现上，通常采用in-place排序（即只需用到O(1)的额外空间的排序），因而在从后向前扫描过程中，需要反复把已排序元素逐步向后挪位，为最新元素提供插入空间。
     * <p>
     * 一般来说，插入排序都采用in-place在数组上实现。具体算法描述如下：
     * <p>
     * 从第一个元素开始，该元素可以认为已经被排序；
     * <p>
     * 取出下一个元素，在已经排序的元素序列中从后向前扫描；
     * <p>
     * 如果该元素（已排序）大于新元素，将该元素移到下一位置；
     * <p>
     * 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置；
     * <p>
     * 将新元素插入到该位置后；
     * <p>
     * 重复步骤2~5。
     */
    public int[] insertionSort(int[] arr) {
        if (null == arr || arr.length < 1) { return arr; }
        int length = arr.length;
        int preIndex, current;
        for (int i = 1; i < length; i++) {
            preIndex = i - 1;
            current = arr[i];
            while (preIndex >= 0 && arr[preIndex] > current) {
                arr[preIndex + 1] = arr[preIndex];
                preIndex--;
            }
            arr[preIndex + 1] = current;
        }
        return arr;
    }

    /**
     * 希尔排序是希尔（Donald Shell）于1959年提出的一种排序算法。希尔排序也是一种插入排序，它是简单插入排序经过改进之后的一个更高效的版本，也称为缩小增量排序，同时该算法是冲破O(n2）的第一批算法之一。它与插入排序的不同之处在于，它会优先比较距离较远的元素。希尔排序又叫缩小增量排序。
     * <p>
     * 希尔排序是把记录按下表的一定增量分组，对每组使用直接插入排序算法排序；随着增量逐渐减少，每组包含的关键词越来越多，当增量减至1时，整个文件恰被分成一组，算法便终止。
     * <p>
     * 我们来看下希尔排序的基本步骤，在此我们选择增量gap=length/2，缩小增量继续以gap = gap/2的方式，这种增量选择我们可以用一个序列来表示，{n/2,(n/2)/2...1}，称为增量序列。希尔排序的增量序列的选择与证明是个数学难题，我们选择的这个增量序列是比较常用的，也是希尔建议的增量，称为希尔增量，但其实这个增量序列不是最优的。此处我们做示例使用希尔增量。
     * <p>
     * 先将整个待排序的记录序列分割成为若干子序列分别进行直接插入排序，具体算法描述：
     * <p>
     * 选择一个增量序列t1，t2，…，tk，其中ti>tj，tk=1；
     * <p>
     * 按增量序列个数k，对序列进行k 趟排序；
     * <p>
     * 每趟排序，根据对应的增量ti，将待排序列分割成若干长度为m 的子序列，分别对各子表进行直接插入排序。仅增量因子为1 时，整个序列作为一个表来处理，表长度即为整个序列的长度。
     */
    public static int[] shellSort(int[] arr) {
        if (null == arr || arr.length < 1) { return arr; }
        int len = arr.length;
        int temp, gap = len / 2;
        while (gap > 0) {
            for (int i = gap; i < len; i++) {
                temp = arr[i];
                int preIndex = i - gap;
                while (preIndex >= 0 && arr[preIndex] > temp) {
                    arr[preIndex + gap] = arr[preIndex];
                    preIndex -= gap;
                }
                arr[preIndex + gap] = temp;
            }
            gap /= 2;
        }
        return arr;
    }

    /**
     * 和选择排序一样，归并排序的性能不受输入数据的影响，但表现比选择排序好的多，因为始终都是O(n log n）的时间复杂度。代价是需要额外的内存空间。
     * <p>
     * 归并排序是建立在归并操作上的一种有效的排序算法。该算法是采用分治法（Divide and Conquer）的一个非常典型的应用。
     * <p>
     * 归并排序是一种稳定的排序方法。将已有序的子序列合并，得到完全有序的序列；即先使每个子序列有序，再使子序列段间有序。若将两个有序表合并成一个有序表，称为2-路归并。
     * <p>
     * 把长度为n的输入序列分成两个长度为n/2的子序列；
     * <p>
     * 对这两个子序列分别采用归并排序；
     * <p>
     * 将两个排序好的子序列合并成一个最终的排序序列。
     */
    public static int[] MergeSort(int[] array) {
        if (array.length < 2) { return array; }
        int mid = array.length / 2;
        int[] left = Arrays.copyOfRange(array, 0, mid);
        int[] right = Arrays.copyOfRange(array, mid, array.length);
        return merge(MergeSort(left), MergeSort(right));
    }

    /**
     * 归并排序——将两段排序好的数组结合成一个排序数组
     */
    public static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        for (int index = 0, i = 0, j = 0; index < result.length; index++) {
            if (i >= left.length) {
                result[index] = right[j++];
            } else if (j >= right.length) {
                result[index] = left[i++];
            } else if (left[i] > right[j]) {
                result[index] = right[j++];
            } else {
                result[index] = left[i++];
            }
        }
        return result;
    }

    /**
     * 快速排序方法
     * <p>
     * 快速排序的基本思想：通过一趟排序将待排记录分隔成独立的两部分，其中一部分记录的关键字均比另一部分的关键字小，则可分别对这两部分记录继续进行排序，以达到整个序列有序。
     * <p>
     * 快速排序使用分治法来把一个串（list）分为两个子串（sub-lists）。具体算法描述如下：
     * <p>
     * 从数列中挑出一个元素，称为 “基准”（pivot）；
     * <p>
     * 重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；
     * <p>
     * 递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序。
     */
    public static int[] QuickSort(int[] array, int start, int end) {
        if (array.length < 1 || start < 0 || end >= array.length || start > end) { return null; }
        int smallIndex = partition(array, start, end);
        if (smallIndex > start) { QuickSort(array, start, smallIndex - 1); }
        if (smallIndex < end) { QuickSort(array, smallIndex + 1, end); }
        return array;
    }

    /**
     * 快速排序算法——partition
     */
    public static int partition(int[] array, int start, int end) {
        int pivot = (int)(start + Math.random() * (end - start + 1));
        int smallIndex = start - 1;
        swap(array, pivot, end);
        for (int i = start; i <= end; i++) {
            if (array[i] <= array[end]) {
                smallIndex++;
                if (i > smallIndex) {
                    swap(array, i, smallIndex);
                }
            }
        }
        return smallIndex;
    }

    /**
     * 交换数组内两个元素
     */
    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * 堆排序（Heapsort）是指利用堆这种数据结构所设计的一种排序算法。堆积是一个近似完全二叉树的结构，并同时满足堆积的性质：即子结点的键值或索引总是小于（或者大于）它的父节点。
     * <p>
     * 将初始待排序关键字序列(R1,R2….Rn)构建成大顶堆，此堆为初始的无序区；
     * <p>
     * 将堆顶元素R[1]与最后一个元素R[n]交换，此时得到新的无序区(R1,R2,……Rn-1)和新的有序区(Rn),且满足R[1,2…n-1]<=R[n]；
     * <p>
     * 由于交换后新的堆顶R[1]可能违反堆的性质，因此需要对当前无序区(R1,R2,……Rn-1)调整为新堆，然后再次将R[1]与无序区最后一个元素交换，得到新的无序区(R1,R2….Rn-2)和新的有序区(Rn-1,Rn)。不断重复此过程直到有序区的元素个数为n-1，则整个排序过程完成。
     */
    public static int[] HeapSort(int[] array) {
        int len = array.length;
        if (len < 1) { return array; }
        //1.构建一个最大堆
        buildMaxHeap(len, array);
        //2.循环将堆首位（最大值）与末位交换，然后在重新调整最大堆
        while (len > 0) {
            swap(array, 0, len - 1);
            len--;
            adjustHeap(len, array, 0);
        }
        return array;
    }

    /**
     * 建立最大堆
     */
    public static void buildMaxHeap(int len, int[] array) {
        //从最后一个非叶子节点开始向上构造最大堆
        for (int i = (len / 2 - 1); i >= 0; i--) { //感谢 @让我发会呆 网友的提醒，此处应该为 i = (len/2 - 1)
            adjustHeap(len, array, i);
        }
    }

    /**
     * 调整使之成为最大堆
     *
     * @param array
     * @param i
     */
    public static void adjustHeap(int len, int[] array, int i) {
        int maxIndex = i;
        //如果有左子树，且左子树大于父节点，则将最大指针指向左子树
        if (i * 2 < len && array[i * 2] > array[maxIndex]) { maxIndex = i * 2; }
        //如果有右子树，且右子树大于父节点，则将最大指针指向右子树
        if (i * 2 + 1 < len && array[i * 2 + 1] > array[maxIndex]) { maxIndex = i * 2 + 1; }
        //如果父节点不是最大值，则将父节点与最大值交换，并且递归调整与父节点交换的位置。
        if (maxIndex != i) {
            swap(array, maxIndex, i);
            adjustHeap(len, array, maxIndex);
        }
    }

    /**
     * 计数排序的核心在于将输入的数据值转化为键存储在额外开辟的数组空间中。 作为一种线性时间复杂度的排序，计数排序要求输入的数据必须是有确定范围的整数。
     * <p>
     * 计数排序(Counting sort)是一种稳定的排序算法。计数排序使用一个额外的数组C，其中第i个元素是待排序数组A中值等于i的元素的个数。然后根据数组C来将A中的元素排到正确的位置。它只能对整数进行排序。
     * <p>
     * 找出待排序的数组中最大和最小的元素；
     * <p>
     * 统计数组中每个值为i的元素出现的次数，存入数组C的第i项；
     * <p>
     * 对所有的计数累加（从C中的第一个元素开始，每一项和前一项相加）；
     * <p>
     * 反向填充目标数组：将每个元素i放在新数组的第C(i)项，每放一个元素就将C(i)减去1。
     */
    public static int[] CountingSort(int[] array) {
        if (array.length == 0) { return array; }
        int bias, min = array[0], max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) { max = array[i]; }
            if (array[i] < min) { min = array[i]; }
        }
        bias = 0 - min;
        int[] bucket = new int[max - min + 1];
        Arrays.fill(bucket, 0);
        for (int i = 0; i < array.length; i++) {
            bucket[array[i] + bias]++;
        }
        int index = 0, i = 0;
        while (index < array.length) {
            if (bucket[i] != 0) {
                array[index] = i - bias;
                bucket[i]--;
                index++;
            } else { i++; }
        }
        return array;
    }

    /**
     * 桶排序是计数排序的升级版。它利用了函数的映射关系，高效与否的关键就在于这个映射函数的确定。
     * <p>
     * 桶排序 (Bucket sort)的工作的原理：假设输入数据服从均匀分布，将数据分到有限数量的桶里，每个桶再分别排序（有可能再使用别的排序算法或是以递归方式继续使用桶排序进行排
     * <p>
     * 人为设置一个BucketSize，作为每个桶所能放置多少个不同数值（例如当BucketSize==5时，该桶可以存放｛1,2,3,4,5｝这几种数字，但是容量不限，即可以存放100个3）；
     * <p>
     * 遍历输入数据，并且把数据一个一个放到对应的桶里去；
     * <p>
     * 对每个不是空的桶进行排序，可以使用其它排序方法，也可以递归使用桶排序；
     * <p>
     * 从不是空的桶里把排好序的数据拼接起来。
     */
    public static ArrayList<Integer> BucketSort(ArrayList<Integer> array, int bucketSize) {
        if (array == null || array.size() < 2) { return array; }
        int max = array.get(0), min = array.get(0);
        // 找到最大值最小值
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i) > max) { max = array.get(i); }
            if (array.get(i) < min) { min = array.get(i); }
        }
        int bucketCount = (max - min) / bucketSize + 1;
        ArrayList<ArrayList<Integer>> bucketArr = new ArrayList<>(bucketCount);
        ArrayList<Integer> resultArr = new ArrayList<>();
        for (int i = 0; i < bucketCount; i++) {
            bucketArr.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < array.size(); i++) {
            bucketArr.get((array.get(i) - min) / bucketSize).add(array.get(i));
        }
        for (int i = 0; i < bucketCount; i++) {
            if (bucketSize == 1) { // 如果带排序数组中有重复数字时  感谢 @见风任然是风 朋友指出错误
                for (int j = 0; j < bucketArr.get(i).size(); j++) { resultArr.add(bucketArr.get(i).get(j)); }
            } else {
                if (bucketCount == 1) { bucketSize--; }
                ArrayList<Integer> temp = BucketSort(bucketArr.get(i), bucketSize);
                for (int j = 0; j < temp.size(); j++) { resultArr.add(temp.get(j)); }
            }
        }
        return resultArr;
    }

    /**
     * 基数排序也是非比较的排序算法，对每一位进行排序，从最低位开始排序，复杂度为O(kn),为数组长度，k为数组中的数的最大的位数；
     * <p>
     * 基数排序是按照低位先排序，然后收集；再按照高位排序，然后再收集；依次类推，直到最高位。有时候有些属性是有优先级顺序的，先按低优先级排序，再按高优先级排序。最后的次序就是高优先级高的在前，高优先级相同的低优先级高的在前。基数排序基于分别排序，分别收集，所以是稳定的。
     * <p>
     * 取得数组中的最大数，并取得位数；
     * <p>
     * arr为原始数组，从最低位开始取每个位组成radix数组；
     * <p>
     * 对radix进行计数排序（利用计数排序适用于小范围数的特点）；
     */
    public static int[] RadixSort(int[] array) {
        if (array == null || array.length < 2) { return array; }
        // 1.先算出最大数的位数；
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            max = Math.max(max, array[i]);
        }
        int maxDigit = 0;
        while (max != 0) {
            max /= 10;
            maxDigit++;
        }
        int mod = 10, div = 1;
        ArrayList<ArrayList<Integer>> bucketList = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < 10; i++) {
            bucketList.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < maxDigit; i++, mod *= 10, div *= 10) {
            for (int j = 0; j < array.length; j++) {
                int num = (array[j] % mod) / div;
                bucketList.get(num).add(array[j]);
            }
            int index = 0;
            for (int j = 0; j < bucketList.size(); j++) {
                for (int k = 0; k < bucketList.get(j).size(); k++) {
                    array[index++] = bucketList.get(j).get(k);
                }
                bucketList.get(j).clear();
            }
        }
        return array;
    }
}