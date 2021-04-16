package CrackingTheCodeInterview.stack;

import java.util.LinkedList;

/**
 * @Author: HB
 * @Description: 面试题03.06 - 动物收容所
 *               描述: 动物收容所。有家动物收容所只收容狗与猫，且严格遵守“先进先出”的原则。
 *               在收养该收容所的动物时，收养人只能收养所有动物中“最老”（由其进入收容所的时间长短而定）
 *               的动物，或者可以挑选猫或狗（同时必须收养此类动物中“最老”的）。换言之，收养人不能自由挑选想收养的对象。
 *               请创建适用于这个系统的数据结构，实现各种操作方法，比如enqueue、dequeueAny、dequeueDog和dequeueCat。
 *               允许使用Java内置的LinkedList数据结构。
 *               enqueue方法有一个animal参数，animal[0]代表动物编号，animal[1]代表动物种类，其中 0 代表猫，1 代表狗。
 *               dequeue*方法返回一个列表[动物编号, 动物种类]，若没有可以收养的动物，则返回[-1,-1]。
 *               Case:
 *               Input:  ["AnimalShelf", "enqueue", "enqueue", "dequeueCat", "dequeueDog", "dequeueAny"]
 *                       [[], [[0, 0]], [[1, 0]], [], [], []]
 *               Output: [null,null,null,[0,0],[-1,-1],[1,0]]
 *               Limit:
 *               Remark:
 * @CreateDate: 11:20 2021/4/16
 */

public class Q23 {
    
    /**
     * @Author: HB
     * @Description: 单队列解法
     * @Date: 11:28 2021/4/16
     * @Params: null
     * @Returns: 
    */
    class AnimalShelf {
        // 创建一个先进先出的队列
        LinkedList<int[]> animalQueue = new LinkedList<>();
        public AnimalShelf() {

        }

        public void enqueue(int[] animal) {
            animalQueue.offer(animal);
        }

        public int[] dequeueAny() {
            if (animalQueue.size() == 0)
                return new int[]{-1,-1};
            return animalQueue.poll();
        }

        public int[] dequeueDog() {
            int animalNums = animalQueue.size();
            if (animalNums == 0)
                return new int[]{-1,-1};
            // 找到最先进入的狗狗
            for (int i = 0; i < animalNums; i++) {
                if (animalQueue.get(i)[1] == 1) {
                    return animalQueue.remove(i);
                }
            }
            return new int[]{-1, -1};
        }

        public int[] dequeueCat() {
            int animalNums = animalQueue.size();
            if (animalNums == 0)
                return new int[]{-1,-1};
            // 找到最先进入的猫猫
            for (int i = 0; i < animalNums; i++) {
                if (animalQueue.get(i)[1] == 0) {
                    return animalQueue.remove(i);
                }
            }
            return new int[]{-1, -1};
        }
    }


    /**
     * @Author: HB
     * @Description: 双队列解法
     * @Date: 11:29 2021/4/16
     * @Params: null
     * @Returns:
    */
    class AnimalShelfByTwoQueue {
        // 双队列解法 - 一个狗狗队列/一个猫猫队列
        LinkedList<int[]> dogQueue = new LinkedList<>();
        LinkedList<int[]> catQueue = new LinkedList<>();
        public AnimalShelfByTwoQueue() {

        }

        public void enqueue(int[] animal) {
            if (animal[1] == 1)
                dogQueue.offer(animal);
            else
                catQueue.offer(animal);
        }

        public int[] dequeueAny() {
            int[] dog = null;
            if (!dogQueue.isEmpty()) {
                dog = dogQueue.peek();
            } else if (!catQueue.isEmpty()) {
                return catQueue.poll();
            } else {
                return new int[]{-1,-1};
            }

            int[] cat = null;
            if (!catQueue.isEmpty()) {
                cat = catQueue.peek();
            } else if (!dogQueue.isEmpty()) {
                return dogQueue.poll();
            }

            // 比较编号, 编号小的先返回
            if (dog[0] <= cat[0])
                return dogQueue.poll();
            else
                return catQueue.poll();
        }

        public int[] dequeueDog() {
            if (!dogQueue.isEmpty())
                return dogQueue.poll();
            return new int[]{-1, -1};
        }

        public int[] dequeueCat() {
            if (!catQueue.isEmpty())
                return catQueue.poll();
            return new int[]{-1, -1};
        }
    }
}
