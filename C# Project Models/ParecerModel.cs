using System.ComponentModel;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using StudentGrades.Models.Common;

namespace DiscPareceres.Business.Models
{
    public class ParecerModel : BaseEntity
    {
        [ForeignKey("Student")]
        public int StudentId { get; set; }
        public StudentModel Student { get; set; }
        [ForeignKey("Level")]
        public int LevelId { get; set; }
        public LevelModel Level { get; set; }
        public int ParecerNumber { get; set; }
        public double Speaking { get; set; } = 0;
        public double TeacherSpeaking { get; set; } = 0;
        public double SpeakingAverage { get { return GetAverage(Speaking, TeacherSpeaking); } }
        public string SpeakingFeedback { get { return GetSpeakingFeedback(SpeakingAverage); } }
        public double Reading { get; set; } = 0;
        public double TeacherReading { get; set; } = 0;
        public double ReadingAverage { get { return GetAverage(Reading, TeacherReading);} }
        public string ReadingFeedback { get { return GetReadingFeedback(ReadingAverage);} }
        public double Listening { get; set; } = 0;
        public double TeacherListening { get; set; } = 0;
        public double ListeningAverage { get { return GetAverage(Listening, TeacherListening);} }
        public string ListeningFeedback { get { return GetListeningFeedback(ListeningAverage);} }
        public double Grammar { get; set; } = 0;
        public double TeacherGrammar { get; set; } = 0;
        public double GrammarAverage { get { return GetAverage(Grammar, TeacherGrammar);} }
        public string GrammarFeedback { get { return GetGrammarFeedback(GrammarAverage);} }
        public double Writing { get; set; } = 0;
        public double TeacherWriting { get; set; } = 0;
        public double WritingAverage { get { return GetAverage(Writing, TeacherWriting);} }
        public string WritingFeedback { get { return GetWritingFeedback(WritingAverage);} }
        public double ClassPerformance { get; set; } = 0;
        public string ClassPerformanceFeedback { get { return GetClassPerformanceFeedback(ClassPerformance);} }
        public double ParecerGrade { get { return GetParecerGrade(); } }
        public string? Comments { get; set; }

        public double GetParecerGrade()
        {
            try
            {
                double[] averages = new double[] {
                SpeakingAverage,
                ReadingAverage,
                ListeningAverage,
                GrammarAverage,
                WritingAverage
            };
                return Math.Round(averages.Average() * 10);
            }
            catch
            {
                return 0;
            }
            
        }

        public double GetAverage(double grade1, double grade2)
        {
            return Math.Round((grade1 + grade2) / 2, 1);
        }

        public int GetFinalGrade(ParecerModel parecerModel)
        {
            ParecerModel p1;
            ParecerModel p2;

            if(parecerModel.ParecerNumber == 1)
            {
                p1 = parecerModel;
                p2 = parecerModel.Student.Pareceres.FirstOrDefault(p => p.LevelId == p1.LevelId && p.Id != p1.Id, new ParecerModel());
            }
            else
            {
                p2 = parecerModel;
                p1 = parecerModel.Student.Pareceres.FirstOrDefault(p => p.LevelId == p2.LevelId && p.Id != p2.Id, new ParecerModel());
            }
            
            if (p1 == null || p1.ParecerGrade == 0)
            {
                return (int) p2.ParecerGrade;
            }
            if (p2 == null || p2.ParecerGrade == 0)
            {
                return (int)p1.ParecerGrade;
            }
            try
            {
                double[] studentGrades = new double[] {
                p1.SpeakingAverage,
                p1.ReadingAverage,
                p1.ListeningAverage,
                p1.GrammarAverage,
                p1.WritingAverage,
                p2.SpeakingAverage,
                p2.ReadingAverage,
                p2.ListeningAverage,
                p2.GrammarAverage,
                p2.WritingAverage
            };

                return (int)Math.Round(studentGrades.Average() * 10);
            }
            catch
            {
                return 0;
            }
        }

        public int GetFinalGrade(ParecerModel? p1, ParecerModel? p2)
        {
            if (p1 == null || p1.ParecerGrade == 0)
            {
                return (int)p2.ParecerGrade;
            }
            if (p2 == null || p2.ParecerGrade == 0)
            {
                return (int)p1.ParecerGrade;
            }
            try
            {
                double[] studentGrades = new double[] {
                p1.SpeakingAverage,
                p1.ReadingAverage,
                p1.ListeningAverage,
                p1.GrammarAverage,
                p1.WritingAverage,
                p2.SpeakingAverage,
                p2.ReadingAverage,
                p2.ListeningAverage,
                p2.GrammarAverage,
                p2.WritingAverage
            };

                return (int)Math.Round(studentGrades.Average() * 10);
            }
            catch
            {
                return 0;
            }
        }

        public string GetSpeakingFeedback(double avg)
        {
            if (avg >= 9)
            {
                return "Apresenta boa fluência e desenvoltura na fala, e faz uso adequado e constante do inglês em sala de aula. Possui boa pronúncia e entonação, e faz uso adequado de vocabulário e estruturas pertinentes ao nível. Continue assim!";
            }
            else if (avg >= 8)
            {
                return "Apresenta boa desenvoltura na fala, e faz uso adequado do inglês em sala de aula. Tente sempre colocar em prática o que aprendeu em aula, e não tenha medo de errar. Solte a língua! Buscar sempre mais conhecimento e interação é o caminho para atingir notas ainda melhores!";
            }
            else if (avg >= 7)
            {
                return "Apresenta desenvoltura satisfatória, com alguma fluência na fala. Tente sempre colocar em prática o que aprendeu em aula, e não tenha medo de errar. Dessa maneira, sua desenvoltura nessa habilidade ficará cada vez melhor! Solte a língua! Buscar sempre mais conhecimento e interação é o caminho para atingir notas ainda melhores!";
            }
            else if (avg >= 6)
            {
                return "Hey, estamos sentindo falta de ouvir a sua voz! Interagir em sala de aula, com seu professor e colegas, é a chave para um bom desenvolvimento da sua habilidade de fala. Tente utilizar as estruturas e vocabulário que aprendemos, e não tenha medo de errar - errar é uma experiência de aprendizado. Busque usar o inglês ao máximo, e não tenha receio de pedir ajuda ao professor. Recorrer ao português deve ser um recurso utilizado apenas em situações de tradução de palavras novas. Se tiver oportunidade, fale inglês fora da sala de aula também.";
            }
            else
            {
                return "Seu desempenho nesta habilidade inspira cuidados. Interagir em sala de aula, com seu professor e colegas, é a chave para um bom desenvolvimento da sua habilidade de fala. Tente utilizar as estruturas e vocabulário que aprendemos, e não tenha medo de errar - errar é uma experiência de aprendizado. Busque usar o inglês ao máximo, e não tenha receio de pedir ajuda ao professor. Recorrer ao português deve ser um recurso utilizado apenas em situações de tradução de palavras novas. Se tiver oportunidade, fale inglês fora da sala de aula também.";
            }
        }

        public string GetReadingFeedback(double avg)
        {
            if (avg >= 9)
            {
                return "Apresenta ótima compreensão escrita, faz bom uso de estratégias ao realizar exercícios de reading e consegue entender o contexto geral dos textos trabalhados. Facilidade em captar os detalhes específicos pedidos. Ótima desenvoltura!";
            }
            else if (avg >= 8)
            {
                return "Apresenta ótima compreensão escrita, faz bom uso de estratégias ao realizar exercícios de reading e consegue entender o contexto geral dos textos trabalhados, com pequenos deslizes no entendimento de detalhes específicos. Continue o bom trabalho, e lembre-se sempre de praticar essa habilidade cercando-se de conteúdo relevante em inglês. Leia sobre aquilo que lhe é interessante, e descubra coisas novas sobre aquilo que não é! Continue assim para progredir cada vez mais!";
            }
            else if (avg >= 7)
            {
                return "Apresenta boa compreensão escrita, faz uso de estratégias ao realizar exercícios de reading e consegue entender o contexto geral dos textos trabalhados. Apresenta alguma dificuldade em detalhes específicos. Desenvolva ainda mais essa habilidade lendo textos de gêneros variados, inclusive os que não leria habitualmente. Divirta-se e descubra coisas novas em inglês! Continue assim para progredir cada vez mais!";
            }
            else if (avg >= 6)
            {
                return "Compreende o contexto geral dos textos apresentados, e apresenta grande potencial de aprimoramento da habilidade. Estratégias para otimizar a leitura e captar detalhes específicos são a chave do sucesso - converse com o seu professor a respeito. Ler é um ótimo exercício para polir e expandir seu vocabulário; não tenha medo de revisar os textos utilizados em aula e buscar novas descobertas em livros e na internet. Aqui vão algumas sugestões: faça uma rápida leitura inicial para entender a ideia geral do texto com o qual está lidando. Leia sempre com cuidado a atividade proposta e suas questões. Cultive sempre o hábito da leitura, e divirta-se. Ler nos traz experiências únicas!";
            }
            else
            {
                return "Seu desempenho nesta habilidade inspira cuidados. Estratégias para otimizar a leitura e captar detalhes específicos são a chave do sucesso - converse com o seu professor a respeito. Ler é um ótimo exercício para polir e expandir seu vocabulário; não tenha medo de revisar os textos utilizados em aula e buscar novas descobertas em livros e na internet. Aqui vão algumas sugestões: faça uma rápida leitura inicial para entender a ideia geral do texto com o qual está lidando. Leia sempre com cuidado a atividade proposta e suas questões. Cultive sempre o hábito da leitura, e divirta-se. Ler nos traz experiências únicas!";
            }
        }

        public string GetListeningFeedback(double avg)
        {
            if (avg >= 9)
            {
                return "Ótimo entendimento do que é dito e de todas as atividades envolvendo listening. Consegue entender o contexto geral nas passagens de áudio, bem como captar os detalhes específicos pedidos. Faz bom uso de estratégias ao realizar exercícios de listening. Continue assim!";
            }
            else if (avg >= 8)
            {
                return "Bom entendimento do que é dito e de todas as atividades envolvendo listening. Consegue entender o contexto geral nas passagens de áudio, bem como captar os detalhes específicos pedidos. Faz bom uso de estratégias ao realizar exercícios de listening. Procure assistir filmes/vídeos/séries e ouvir músicas em inglês com sotaques variados - isso pode ajudar muito em seu progresso nessa habilidade. Bom trabalho, busque aprimorar-se ainda mais!";
            }
            else if (avg >= 7)
            {
                return "Bom entendimento do que é dito e de atividades envolvendo listening. Consegue entender o contexto geral nas passagens de áudio, bem como captar os detalhes específicos pedidos com algum esforço. Faz bom uso de estratégias ao realizar exercícios de listening. Procure assistir filmes/vídeos/séries e ouvir músicas em inglês com sotaques variados - isso pode ajudar muito em seu progresso nessa habilidade. Bom trabalho, busque aprimorar-se ainda mais!";
            }
            else if (avg >= 6)
            {
                return "Sabe aquela música que não sai da sua cabeça? Ela pode ser uma grande aliada no desenvolvimento dessa habilidade! Como todas as outras, ela precisa de prática. Tente entender a ideia geral do que está sendo dito - ao invés de focar em palavra por palavra. Leia com cuidado as atividades antes de ouvir o áudio, e destaque palavras que podem ajudá-lo a resolver questões. Converse com o seu professor a respeito de estratégias para aprimorar sua habilidade de entender detalhes específicos. Em casa, assista aquele filme que você gosta, ouça aquela música que você quer muito aprender a cantar. Quem disse que aprender inglês tem que ser chato? Aprimorar o listening pode ser divertido!";
            }
            else
            {
                return "Seu desempenho nesta habilidade inspira cuidados. Sabe aquela música que não sai da sua cabeça? Ela pode ser uma grande aliada no desenvolvimento dessa habilidade! Como todas as outras, ela precisa de prática. Tente entender a ideia geral do que está sendo dito - ao invés de focar em palavra por palavra. Leia com cuidado as atividades antes de ouvir o áudio, e destaque palavras que podem ajudá-lo a resolver questões. Converse com o seu professor a respeito de estratégias para aprimorar sua habilidade de entender detalhes específicos. Em casa, assista aquele filme que você gosta, ouça aquela música que você quer muito aprender a cantar. Quem disse que aprender inglês tem que ser chato? Aprimorar o listening pode ser divertido!";
            }
        }

        public string GetGrammarFeedback(double avg)
        {
            if (avg >= 9)
            {
                return "Apresenta facilidade com os aspectos gramaticais do idioma, utiliza estruturas gramaticais aprendidas previamente e coloca novas estruturas em prática durante a aula e nos temas de casa. Ótimo trabalho!";
            }
            else if (avg >= 8)
            {
                return "Utiliza estruturas gramaticais aprendidas previamente com boa precisão e coloca novas estruturas em prática durante a aula e nos temas de casa. Sempre que necessário, revise as estruturas anteriores e tire suas dúvidas com seu professor. Busque usar, sempre que possível, as estruturas que aprendeu em sua fala e escrita. Mantenha o bom trabalho!";
            }
            else if (avg >= 7)
            {
                return "Utiliza estruturas gramaticais aprendidas previamente com boa desenvoltura e com alguma frequência coloca novas estruturas em prática durante a aula e nos temas de casa. Sempre que necessário, revise as estruturas anteriores e tire suas dúvidas com seu professor. Busque usar, sempre que possível, as estruturas e vocabulário que aprendeu em sua fala e escrita. Você está no caminho certo.";
            }
            else if (avg >= 6)
            {
                return "Um novo nível sempre apresenta novos desafios - e você está no caminho certo! Busque sempre colocar em prática as estruturas e vocabulário que aprendemos em aula, e tire dúvidas sempre que possível. Quando necessário, revise as estruturas que aprendemos anteriormente. A prática faz a perfeição, portanto, mantenha o foco, realize suas atividades e deveres de casa e busque maiores resultados e desafios!";
            }
            else
            {
                return "Seu desempenho nesta habilidade inspira cuidados. Busque sempre colocar em prática as estruturas e vocabulário que aprendemos em aula, e tire dúvidas sempre que possível. Quando necessário, revise as estruturas que aprendemos anteriormente. A prática faz a perfeição, portanto, mantenha o foco, realize suas atividades e deveres de casa e busque maiores resultados e desafios!";
            }
        }

        public string GetWritingFeedback(double avg)
        {
            if (avg >= 9)
            {
                return "Expressa claramente suas ideias e as conecta de forma coerente. Faz uso de estruturas pertinentes ao nível, cumpre as instruções e atinge os objetivos dos textos. Busque sempre diversificar seu vocabulário ainda mais! Excelente trabalho!";
            }
            else if (avg >= 8)
            {
                return "Expressa de maneira muito satisfatória suas ideias e as conecta de forma coerente. Faz bom uso de estruturas pertinentes ao nível, cumpre em sua maior parte as instruções e atinge os objetivos dos textos. Busque sempre diversificar seu vocabulário ainda mais, e não tenha medo de ser ambicioso em seus textos. Esperamos ansiosos por sua próxima aventura na escrita!";
            }
            else if (avg >= 7)
            {
                return "Expressa de maneira satisfatória suas ideias e as conecta de forma coerente. Faz uso de estruturas pertinentes ao nível, cumpre em sua maior parte as instruções e atinge os objetivos dos textos. Busque sempre diversificar seu vocabulário, utilizar as estruturas que aprendemos e ser ambicioso em seus textos. Continue desbravando os misteriosos mundos da escrita!";
            }
            else if (avg >= 6)
            {
                return "Grandes ideias são o início de todo bom texto - mas nem sempre é fácil colocá-las no papel. Para isso, tente organizar suas ideias e decidir onde quer chegar com seu trabalho, e tente sempre utilizar as estruturas e vocabulários que aprendemos em aula. Antes de entregar seu texto, verifique sua ortografia, pontuação, se conseguiu atingir todos os objetivos que a questão pede. Seu texto faz sentido? Diz aquilo que você queria dizer? A escrita, como qualquer habilidade, requer prática. Converse com o seu professor caso necessite de apoio.";
            }
            else
            {
                return "Seu desempenho nesta habilidade inspira cuidados. Tente organizar suas ideias e decidir onde quer chegar com seu trabalho, e tente sempre utilizar as estruturas e vocabulários que aprendemos em aula. Antes de entregar seu texto, verifique sua ortografia, pontuação, se conseguiu atingir todos os objetivos que a questão pede. Seu texto faz sentido? Diz aquilo que você queria dizer? A escrita, como qualquer habilidade, requer prática. Converse com o seu professor caso necessite de apoio.";
            }
        }

        public string GetClassPerformanceFeedback(double avg)
        {
            if (avg >= 9)
            {
                return "Muito participativo, tem boa interação com colegas e professor. É assíduo e solícito. Mantenha o ótimo desempenho!";
            }
            else if (avg >= 8)
            {
                return "Participativo, tem boa interação com colegas e professor. É assíduo e solícito, e busca tirar dúvidas e fazer deveres de casa. Busca estar envolvido nas discussões e brincadeiras feitas em aula, é pontual e comprometido com suas tarefas. Ótimo desempenho!";
            }
            else if (avg >= 7)
            {
                return "Participativo, tem boa interação com colegas e professor. Em geral, é assíduo e solícito, e busca tirar dúvidas e fazer deveres de casa. Busque sempre estar envolvido nas discussões e atividades feitas em aula, ser pontual e comprometido com suas tarefas. Você já está no caminho - estamos quase lá!";
            }
            else if (avg >= 6)
            {
                return "Participa com alguma frequência, e pode interagir cada vez mais com colegas e professor. Busque sempre ser assíduo, tirar suas dúvidas e fazer os deveres de casa. Envolva-se nas discussões e atividades feitas em aula, seja pontual e comprometido com o seu próprio desenvolvimento. A aula fica mais divertida quando participamos ativamente!";
            }
            else
            {
                return "Seu desempenho nesta habilidade inspira cuidados. Pode interagir cada vez mais com colegas e professor. Busque sempre ser assíduo, tirar suas dúvidas e fazer os deveres de casa. Envolva-se nas discussões e atividades feitas em aula, seja pontual e comprometido com o seu próprio desenvolvimento. A aula fica mais divertida quando participamos ativamente!";
            }
        }
    }

    
}
