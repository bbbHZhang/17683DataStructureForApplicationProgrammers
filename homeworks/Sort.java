import java.util.Random;

public class Sort {
    public static void main(String[] args) {
        Sort s = new Sort();
        int size = 10;
        Random r = new Random();
        int[] array = new int[size];

        //new array and Bubble sort
        while (size != 1) {
            array[--size] = r.nextInt(200);
        }
        StringBuilder sb = new StringBuilder();
        for (int i: array) {
            sb.append(i).append(", ");
        }
        System.out.println(sb.toString());
        s.quickSortQuizFive(array, 0, array.length - 1);
        System.out.println("Another merge");
        sb = new StringBuilder();
        for (int i: array) {
            sb.append(i).append(", ");
        }
        System.out.println(sb.toString());
        System.out.println();

    }

    public void BubleSort(int[] array) {
        //put the biggest to the end
        //something wrong first the outer bound constriant should start at the length since the in will not reach out
        for (int out = array.length; out > 0; out--) {
            for (int in = 1; in < out; in++) {
                if (array[in] < array[in - 1]) {
                    swap(array, in - 1, in);
                }
            }
        }
    }

    public void SelectionSort(int[] array) {
        //put the smallest at the beginning of the array
        for (int out = 0; out < array.length; out++) {
            int min = out;
            for (int in = out + 1; in < array.length; in++) {
                if (array[in] < array[min]) {
                    min = in;
                }
            }
            if (min != out) {
                swap(array, min, out);
            }
        }
    }

    public void swap(int[] array, int a, int b) {
        int tmp = array[b];
        array[b] = array[a];
        array[a] = tmp;
    }

    public void InsertionSort(int[] array) {
        //insert 1 to n, comparison starts from the previous one
        //shift the bigger one to bigger index position
        for (int out = 1; out < array.length; out++) {
            //out is the one to hold in hand and compare
            int tmp = array[out];
            int in = out;
            //            for (int in = out; in > 0; in--) {
            while (in > 0 && array[in - 1] > tmp) {//compare with tmp!!!!
                array[in] = array[in - 1];
                in--;
            }
            if (in != out) {
                array[in] = tmp;
            }

        }
    }

    public int[] MergeSort(int[] array) {
        if (array.length <= 1) {
            return array;
        }
        int mid = array.length / 2;
        int[] left = new int[mid];
        System.arraycopy(array, 0, left, 0, mid);
        int[] right = new int[array.length - mid];
        System.arraycopy(array, mid, right, 0, array.length - mid);
        left = MergeSort(left);
        right = MergeSort(right);
        return merge(left, right);

    }

    private int[] merge(int[]a, int[] b) {//simpler and fast, another solution
        int index = 0;
        int[] result = new int[a.length + b.length];
        int indexa = 0;
        int indexb = 0;
        while (indexa < a.length && indexb < b.length) {
            if (a[indexa] < b[indexb]) {
                result[index++] = a[indexa++];
            } else {
                result[index++] = b[indexb++];
            }
        }
        while (indexa < a.length) {
            result[index++] = a[indexa++];
        }
        while (indexb < b.length) {
            result[index++] = b[indexb++];
        }
        return result;
    }
    
    //merge sort without creating the new arrays
    public void merges(int[] array) {
        int[] result = new int[array.length];
        mergeHelper(array, result, 0, array.length - 1);
    }
    private void mergeHelper(int[] array, int[] result, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeHelper(array, result, left, mid);
        mergeHelper(array, result, mid + 1, right);
        
        int lp = left;
        int rp = mid + 1;
        int index = left;
        while (lp <= mid && rp <= right) {
            if (array[lp] < array[rp]) {
                result[index++] = array[lp++];
            } else {
                result[index++] = array[rp++];
            }
        }
        while (lp <= mid) {
            result[index++] = array[lp++];
        }
        while (rp <= right) {
            result[index++] = array[rp++];
        }
        System.arraycopy(result, left, array, left, right - left + 1); //missed  + 1. careful about the item number
        
    }
    

    public void QuickSort(int[] array, int left, int right) {
        if (left >= right) { //don't forget the base case
            return;
        }
        int mid = partition(array, left, right);
        QuickSort(array, left, mid - 1);
        QuickSort(array, mid + 1, right);

    }

    private int partition(int[] array, int left, int right) {
        int lp = left - 1;
        int rp = right;
        while (true) {
            while (array[++lp] < array[right]);
            while (rp > 0 && array[--rp] > array[right]);//????????????? rp >0
            if (lp >= rp) {
                break;
            }
            swap(array, lp, rp);
        }
        swap(array, lp, right);
        return lp;
    }
    
    public void quickImproved(int[] array) {
        quickImprovedHelper(array, 0, array.length - 1);
    }
    
    private void quickImprovedHelper(int[] array, int left, int right) {
        int lp = left;
        int rp = right;
        int pivot = array[left + (right - left) / 2];
        while (lp <= rp) {
            while (array[lp] < pivot) {
                lp++;
            }
            while (array[rp] > pivot) {
                rp--;
            }
            if (lp <= rp) {
                swap(array, lp, rp);
                lp++;
                rp--;
            }
        }
        
        if (lp < right) {
            quickImprovedHelper(array, lp, right);
        }
        if (rp > left) {
            quickImprovedHelper(array, left, rp);
        }
    }
    
    public void quickSortQuizFive(int[] array, int left, int right) {
        if (left > right) {
            return;
        }
        int p = partitionFive(array, left, right);
        quickSortQuizFive(array,left, p - 1);
        quickSortQuizFive(array, p + 1, right);
    }
    
    private int partitionFive(int[] array, int left, int right) {
        int l = left;
        for (int i = l; i < right; i ++) {
            if (array[i] < array[right]) {
                swap(array, i, l);
                l++;
            }
        }
        swap(array, l, right);
        return l;
    }
}
