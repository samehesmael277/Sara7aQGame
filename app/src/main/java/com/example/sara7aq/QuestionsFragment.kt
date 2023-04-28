package com.example.sara7aq

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.sara7aq.databinding.FragmentQuestionsBinding
import kotlin.random.Random

class QuestionsFragment : Fragment() {

    private lateinit var binding: FragmentQuestionsBinding

    private lateinit var loadingDialog: LoadingDialog

    private val args: QuestionsFragmentArgs by navArgs()

    private val questions = listOf(
        "ما هو الشيء الذي تحبه في العمل الذي تقوم به؟",
        "هل تعتقد أن السعادة هي الهدف النهائي في الحياة؟",
        "ما هو الأمر الذي تفعله لتبقى منتعشًا ومتحمسًا في الحياة؟",
        "هل تعتقد أن العلاقات عن بعد يمكن أن تنجح؟",
        "ما هو أكثر شيء يسعدك في العطلات؟",
        "هل تفضل الإقامة في الريف أم المدينة؟ ولماذا؟",
        "هل سبق لك وأن أخفت شيئًا عن أي شخص؟ إذا كان الأمر كذلك، فما هو؟",
        "هل تعتقد أن الأخلاق أم القوانين هي التي تحكم تصرفات الأفراد؟",
        "ما هو الشيء الذي تريده أن يعرفه الجميع عنك؟",
        "هل تفضل الانفراد بنفسك أم الاجتماع مع الآخرين؟ ولماذا؟",
        "هل تعتقد أن الحظ يمكن أن يؤثر على نجاح شخص؟",
        "ما هي الصفات التي تجذبك في الأشخاص؟",
        "هل سبق لك وأن شعرت بالإحباط من شيء ما؟ إذا كان الأمر كذلك، فما هو؟",
        "هل تعتقد أن الشخصية تتغير مع الوقت؟",
        "ما هي الصفات التي تريد تحسينها في نفسك؟",
        "ما هو الأمر الذي يجعلك تشعر بالفخر في حياتك؟",
        "هل تريد أن تسافر حول العالم؟ ولماذا؟",
        "هل سبق لك وأن تعرضت للتمييز بسبب بعض الصفات التي تملكها؟",
        "ما هو أكثر شيء تخجل منه في حياتك؟",
        "هل سبق وأن خدعت أحدهم؟ إذا كان الأمر كذلك، فما هي تفاصيل الواقعة؟",
        "هل تعتقد أنك أفضل من معظم الناس في أي شيء؟ إذا كان الأمر كذلك، فما هو؟",
        "هل سبق لك وأن تصرفت بطريقة سيئة مع أحدهم وندمت على ذلك؟ إذا كان الأمر كذلك، فما الذي حدث؟",
        "هل تعتقد أن الحظ يلعب دورًا في حياتك؟ إذا كان الأمر كذلك، فأعطنا مثالًا على ذلك؟",
        "ما هو أكثر شيء تحب القيام به في وقت فراغك؟",
        "هل سبق لك وأن واجهت خوفًا كبيرًا؟ إذا كان الأمر كذلك، فما هو الشيء الذي أخافت منه؟",
        "هل سبق لك وأن حصلت على درجة سيئة في مادة دراسية ما؟ إذا كان الأمر كذلك، فما الذي حدث؟",
        "هل تفضل العمل في فريق صغير أم فريق كبير؟ ولماذا؟",
        "ما هو الأمر الذي تتمنى تحقيقه في المستقبل القريب؟",
        "هل سبق لك وأن شعرت بالحزن بسبب شيء دون أن تستطيع تحديد سببه؟",
        "هل ترغب في العيش في مدينة كبيرة أم صغيرة؟ ولماذا؟",
        "هل تعتقد أن الصداقة بين الرجل والمرأة ممكنة بدون أي مشاعر رومانسية؟",
        "ما هو الشيء الذي تحبه في العمل الذي تقوم به؟",
        "هل تعتقد أن السعادة هي الهدف النهائي في الحياة؟",
        "ما هو الأمر الذي تفعله لتبقى منتعشًا ومتحمسًا في الحياة؟",
        "هل تعتقد أن العلاقات عن بعد يمكن أن تنجح؟",
        "ما هو أكثر شيء يسعدك في العطلات؟",
        "هل تفضل الإقامة في الريف أم المدينة؟ ولماذا؟",
        "هل سبق لك وأن أخفت شيئًا عن أي شخص؟ إذا كان الأمر كذلك، فما هو؟",
        "هل تعتقد أن الأخلاق أم القوانين هي التي تحكم تصرفات الأفراد؟",
        "ما هو الشيء الذي تريده أن يعرفه الجميع عنك؟",
        "هل تفضل الانفراد بنفسك أم الاجتماع مع الآخرين؟ ولماذا؟",
        "هل تعتقد أن الحظ يمكن أن يؤثر على نجاح شخص؟",
        "ما هي الصفات التي تجذبك في الأشخاص؟",
        "هل سبق لك وأن شعرت بالإحباط من شيء ما؟ إذا كان الأمر كذلك، فما هو؟",
        "هل تعتقد أن الشخصية تتغير مع الوقت؟",
        "ما هي الصفات التي تريد تحسينها في نفسك؟",
        "ما هو الأمر الذي يجعلك تشعر بالفخر في حياتك؟",
        "هل تريد أن تسافر حول العالم؟ ولماذا؟",
        "هل سبق لك وأن تعرضت للتمييز بسبب بعض الصفات التي تملكها؟",
        "ما هو الأمر الذي تحبه في أصدقائك؟",
        "ما هو الشيء الذي تحبه في العمل الذي تقوم به؟",
        "هل تعتقد أن السعادة هي الهدف النهائي في الحياة؟",
        "ما هو الأمر الذي تفعله لتبقى منتعشًا ومتحمسًا في الحياة؟",
        "هل تعتقد أن العلاقات عن بعد يمكن أن تنجح؟",
        "ما هو أكثر شيء يسعدك في العطلات؟",
        "هل تفضل الإقامة في الريف أم المدينة؟ ولماذا؟",
        "هل سبق لك وأن أخفت شيئًا عن أي شخص؟ إذا كان الأمر كذلك، فما هو؟",
        "هل تعتقد أن الأخلاق أم القوانين هي التي تحكم تصرفات الأفراد؟",
        "ما هو الشيء الذي تريده أن يعرفه الجميع عنك؟",
        "هل تفضل الانفراد بنفسك أم الاجتماع مع الآخرين؟ ولماذا؟",
        "هل تعتقد أن الحظ يمكن أن يؤثر على نجاح شخص؟",
        "ما هي الصفات التي تجذبك في الأشخاص؟",
        "هل سبق لك وأن شعرت بالإحباط من شيء ما؟ إذا كان الأمر كذلك، فما هو؟",
        "هل تعتقد أن الشخصية تتغير مع الوقت؟",
        "ما هي الصفات التي تريد تحسينها في نفسك؟",
        "ما هو الأمر الذي يجعلك تشعر بالفخر في حياتك؟",
        "هل تريد أن تسافر حول العالم؟ ولماذا؟",
        "هل سبق لك وأن تعرضت للتمييز بسبب بعض الصفات التي تملكها؟",
        "ما هو الأمر الذي تحبه في أصدقائك؟"
    )

    private var lastQuestion: String = questions[0]

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuestionsBinding.inflate(inflater, container, false)
        loadingDialog = LoadingDialog(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            requireContext().showAlertDialog(
                "Back",
                "Are you sure to back you will lost this game",
                "Yes",
                "No"
            ) {
                goToHomeFragment()
            }
        }

        binding.newQBtn.setOnClickListener {
            loadingDialog.show()
            getData()
        }
    }

    private fun getData() {
        val q = getRandomQuestion()
        val from = getRandomNameFrom()
        val to = getRandomNameTo()

        if (validation(from, to, q)) {
            loadingDialog.hide()
            setDataInUi(q, from, to)
        } else {
            getData()
        }
    }

    private fun setDataInUi(q: String, from: String, to: String) {
        lastQuestion = q
        binding.question.text = q
        binding.qFrom.text = from
        binding.qTo.text = to
    }

    private fun validation(from: String, to: String, q: String): Boolean {
        return from != to && q != lastQuestion
    }

    private fun getRandomQuestion(): String {
        val randomIndex = Random.nextInt(questions.size)
        return questions[randomIndex]
    }

    private fun getRandomNameFrom(): String {
        val randomIndex = Random.nextInt(args.nameList.size)
        return args.nameList[randomIndex]
    }

    private fun getRandomNameTo(): String {
        val randomIndex = Random.nextInt(args.nameList.size)
        return args.nameList[randomIndex]
    }

    private fun goToHomeFragment() {
        val id = findNavController().currentDestination?.id

        if (id == R.id.questionsFragment) {
            val action = QuestionsFragmentDirections.actionQuestionsFragmentToHomeFragment()
            findNavController().navigate(action)
        }
    }

    private fun Context.showAlertDialog(
        title: String,
        message: String,
        positiveAction: String,
        negativeAction: String,
        action: () -> Unit
    ) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton(positiveAction) { _, _ ->
            action()
        }
        builder.setNegativeButton(negativeAction) { _, _ -> }
        builder.show()
    }
}